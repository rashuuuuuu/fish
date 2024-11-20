package com.website.admin.user.service.impl;


import com.website.admin.adminActivityLog.service.AdminActivityService;
import com.website.admin.core.constant.EmailSubjectConstant;
import com.website.admin.log.entity.AdminEmailLog;
import com.website.admin.log.mapper.AdminEmailLogMapper;
import com.website.admin.log.mapper.AdminLogMapper;
import com.website.admin.log.repo.AdminEmailLogRepository;
import com.website.admin.user.entity.Admin;
import com.website.admin.user.mapper.AdminMapper;
import com.website.admin.user.model.AdminUserDetailDto;
import com.website.admin.user.model.CreateAdminModel;
import com.website.admin.user.model.FetchAdminDetail;
import com.website.admin.user.model.SearchAdminUserResponse;
import com.website.admin.user.model.request.*;
import com.website.admin.user.repo.AdminRepository;
import com.website.admin.user.repo.impl.AdminUserSearchRepositoryImpl;
import com.website.admin.user.service.AdminService;
import com.website.common.constant.StatusConstant;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.PageableResponse;
import com.website.common.dto.SearchParam;
import com.website.common.dto.SearchResponseWithMapperBuilder;
import com.website.common.dto.request.SendEmailRequest;
import com.website.common.repo.StatusRepository;
import com.website.common.service.MailService;
import com.website.common.service.SearchResponse;
import com.website.common.util.ResponseUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static com.website.common.constant.SearchParamConstant.USERNAME;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final SearchResponse searchResponse;
    private final AdminUserSearchRepositoryImpl adminUserSearchRepository;
    private final StatusRepository statusRepository;
    private final AdminEmailLogRepository adminEmailLogRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminLogMapper adminLogMapper;
    private final AdminActivityService adminActivityService;
    private final AdminEmailLogMapper adminEmailLogMapper;
    private final MailService mailService;
    @Override
    @Transactional
    public Mono<ApiResponse> createAdminUser(CreateAdminModel createAdminModel, Principal connectedUser){
        Optional<Admin> existedAdminUser = adminRepository.findByUsername(createAdminModel.getEmail());
        Optional<Admin> existedNumber = adminRepository.findByMobileNumber(createAdminModel.getMobileNumber());

        if (existedAdminUser.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("This email is already linked to another account. Please use a different email"));
        }

        if (existedNumber.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("The entered mobile number is already linked to another account. Please use a different number"));
        }

        adminMapper.mapToEntity(createAdminModel);
        adminActivityService.logActivity(connectedUser, "Create", "Admin", createAdminModel.getEmail());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin User Created Successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> getAllAdminUsers(SearchParam searchParam) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SearchResponseWithMapperBuilder<Admin, SearchAdminUserResponse> responseBuilder = SearchResponseWithMapperBuilder
                .<Admin, SearchAdminUserResponse>builder().count(adminUserSearchRepository::count)
                .searchData(adminUserSearchRepository::getAll)
                .mapperFunction(this.adminMapper::getAdminUserResponses)
                .searchParam(searchParam).build();
        searchParam.getParam().put(USERNAME, authentication.getName());
        PageableResponse<SearchAdminUserResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Admin users fetched successfully"));

    }

    @Override
    public Mono<ApiResponse<?>> getAdminUserDetails(FetchAdminDetail fetchAdminDetail) {
        Optional<Admin> admin = adminRepository.findByEmail(fetchAdminDetail.getEmail());
        if (admin.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin not found"));
        } else {
            AdminUserDetailDto adminUserDetailDto = adminMapper.getAdminUserDetails(admin.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(adminUserDetailDto, "Admin user fetched successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> updateAdminUser(UpdateAdminRequest updateAdminRequest) {
        Optional<Admin> checkAdmin = adminRepository.findByEmail(updateAdminRequest.getEmail());
        if (checkAdmin.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
        }
        Admin admin = checkAdmin.get();
        if (StatusConstant.DELETED.getName().equals(admin.getStatus().getName())) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
        }
        if (StatusConstant.BLOCKED.getName().equals(admin.getStatus().getName())) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin user is blocked"));
        }
        Optional<Admin> existedNumber = adminRepository.findByMobileNumber(updateAdminRequest.getMobileNumber());
        if (existedNumber.isPresent() && !existedNumber.get().getEmail().equals(updateAdminRequest.getEmail())) {
            return Mono.just(ResponseUtil.getFailureResponse("The mobile number is linked to another account."));
        }
        Admin updatedAdmin = adminMapper.updateAdminUser(updateAdminRequest, admin);
        adminRepository.save(updatedAdmin);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin user updated successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> deleteAdminUser(DeleteAdminRequest deleteAdminRequest, Principal connectedUser) {
        Optional<Admin> checkAdmin = adminRepository.findByEmail(deleteAdminRequest.getEmail());
        if (checkAdmin.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
        }
        if (Objects.equals(checkAdmin.get().getUsername(), connectedUser.getName())) {
            return Mono.just(ResponseUtil.getFailureResponse("Cannot delete yourself!"));
        }
        else {
            Admin admin = checkAdmin.get();
            if (StatusConstant.BLOCKED.getName().equals(admin.getStatus().getName()) || StatusConstant.DELETED.getName().equals(admin.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
            }
            if (admin.isSuperAdmin()) {
                return Mono.just(ResponseUtil.getFailureResponse("SuperAdmin user cannot be deleted!"));
            }
            admin.setStatus(statusRepository.findByName(StatusConstant.DELETED.getName()));
            admin.setActive(false);
            adminActivityService.logActivity(connectedUser, "Delete", "Admin", admin.getEmail());
            adminRepository.save(admin);

            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin user deleted successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> blockAdminUser(BlockAdminRequest blockAdminRequest, Principal connectedUser) {
        Optional<Admin> checkAdmin = adminRepository.findByEmail(blockAdminRequest.getEmail());
        if (checkAdmin.isEmpty() || StatusConstant.DELETED.getName().equals(checkAdmin.get().getStatus().getName())) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Admin user not found"));
        }
        if (Objects.equals(checkAdmin.get().getUsername(), connectedUser.getName())) {
            return Mono.just(ResponseUtil.getFailureResponse("Cannot block yourself!"));
        }
        Admin admin = checkAdmin.get();
        if (!admin.isSuperAdmin()) {
            if (StatusConstant.ACTIVE.getName().equals(checkAdmin.get().getStatus().getName())) {
                admin.setStatus(statusRepository.findByName(StatusConstant.BLOCKED.getName()));
                admin.setActive(false);
                adminRepository.save(admin);
                adminLogMapper.mapToBlockEntity(admin, blockAdminRequest, connectedUser);
                adminActivityService.logActivity(connectedUser, "Block", "Admin", admin.getEmail());
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin user blocked successfully"));
            }
            return Mono.just(ResponseUtil.getFailureResponse("Admin user block unsuccessful"));
        }
        return Mono.just(ResponseUtil.getFailureResponse("SuperAdmin user cannot be blocked!"));
    }

    @Override
    public Mono<ApiResponse<?>> unblockAdminUser(UnblockAdminUserRequest unblockAdminRequest, Principal connectedUser) {
        Optional<Admin> checkAdmin = adminRepository.findByEmail(unblockAdminRequest.getEmail());
        if (checkAdmin.isPresent()) {
            Admin admin = checkAdmin.get();
            if (StatusConstant.BLOCKED.getName().equals(admin.getStatus().getName())) {
                admin.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
                admin.setActive(true);
                adminRepository.save(admin);

                adminLogMapper.mapToUnblockEntity(admin, unblockAdminRequest, connectedUser);
                adminActivityService.logActivity(connectedUser, "Unblock", "Admin", admin.getEmail());
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("Admin user unblocked successfully"));
            }
        }
        return Mono.just(ResponseUtil.getFailureResponse("Admin user unblock failed"));
    }

    @Override
    public Mono<ApiResponse<?>> createAdminPassword(CreatePasswordRequest createPasswordRequest) {
        Optional<AdminEmailLog> adminEmailLog = adminEmailLogRepository.findByUuid(createPasswordRequest.getUuid());
        if (adminEmailLog.isEmpty()) {
            return Mono.just(ResponseUtil.getFailureResponse("Invalid UUID"));
        } else {
            AdminEmailLog existingAdminEmailLog = adminEmailLog.get();
            if (existingAdminEmailLog.isExpired()) {
                return Mono.just(ResponseUtil.getFailureResponse("Link expired"));
            } else {
                Optional<Admin> admin = adminRepository.findByEmail(existingAdminEmailLog.getAdmin().getEmail());
                Admin existingAdmin = admin.get();
                existingAdmin.setPassword(passwordEncoder.encode(createPasswordRequest.getPassword()));
                existingAdmin.setStatus(statusRepository.findByName("ACTIVE"));
                existingAdmin.setPasswordChangeDate(new Date());
                existingAdmin.setActive(true);
                adminRepository.save(existingAdmin);
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("Password created successfully"));

            }
        }
    }

    @Override
    @Transactional
    public Mono<ApiResponse<?>> sendPasswordResetLink(PasswordResetLinkRequest passwordResetLinkRequest, Principal connectedUser) {
        Optional<Admin> optionalAdmin = adminRepository.findByEmail(passwordResetLinkRequest.getEmail());
        if (optionalAdmin.isEmpty()) {
            return Mono.just(ResponseUtil.getFailureResponse("No admin found with the provided email address."));
        } else {
            try {
                Admin admin = optionalAdmin.get();
                AdminEmailLog adminEmailLog = adminEmailLogMapper.mapToResetPasswordEntity(admin);
                SendEmailRequest sendEmailRequest = new SendEmailRequest();
                sendEmailRequest.setRecipient(admin.getEmail());
                sendEmailRequest.setSubject(EmailSubjectConstant.RESET_PASSWORD);
                sendEmailRequest.setMessage(adminEmailLog.getMessage());
                mailService.sendEmail(sendEmailRequest);
                adminActivityService.logActivity(connectedUser, "Reset Password", "Admin", admin.getEmail());
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("Password reset email sent to the admin user."));
            } catch (Exception ex) {
                return Mono.just(ResponseUtil.getFailureResponse("Password reset request failed"));

            }
        }
    }

    @Override
    public Mono<ApiResponse<?>> resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Optional<AdminEmailLog> adminEmailLog = adminEmailLogRepository.findByUuid(resetPasswordRequest.getUuid());
        if (adminEmailLog.isEmpty()) {
            return Mono.just(ResponseUtil.getFailureResponse("Invalid user"));
        } else {
            AdminEmailLog existingAdminEmailLog = adminEmailLog.get();
            if (existingAdminEmailLog.isExpired()) {
                return Mono.just(ResponseUtil.getFailureResponse("Link expired"));
            } else {
                Optional<Admin> admin = adminRepository.findByEmail(existingAdminEmailLog.getAdmin().getEmail());
                Admin existingAdmin = admin.get();
                existingAdmin.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
                existingAdmin.setPasswordChangeDate(new Date());
                adminRepository.save(existingAdmin);
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("Password set successfully."));

            }
        }
    }
}
