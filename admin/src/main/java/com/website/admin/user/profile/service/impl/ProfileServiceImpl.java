package com.website.admin.user.profile.service.impl;

import com.website.admin.adminActivityLog.service.AdminActivityService;
//import com.website.admin.core.constant.EmailSubjectConstant;
import com.website.admin.user.entity.Admin;
import com.website.admin.user.profile.mapper.ProfileMapper;
import com.website.admin.user.profile.model.*;
import com.website.admin.user.profile.service.ProfileService;
import com.website.admin.user.repo.AdminRepository;
import com.website.common.constant.StatusConstant;
import com.website.common.dto.ApiResponse;
//import com.website.common.dto.request.SendEmailRequest;
import com.website.common.repo.StatusRepository;
//import com.website.common.service.MailService;
import com.website.common.util.ResponseUtil;
//import com.website.admin.log.entity.AdminEmailLog;
//import com.website.admin.log.mapper.AdminEmailLogMapper;
//import com.website.admin.log.repo.AdminEmailLogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final AdminRepository adminRepository;
    private final ProfileMapper profileMapper;
    private final PasswordEncoder passwordEncoder;
    private final StatusRepository statusRepository;
//    private final AdminEmailLogMapper adminEmailLogMapper;
//    private final MailService mailService;
    private final AdminActivityService adminActivityService;
//    private final AdminEmailLogRepository adminEmailLogRepository;

    @Override
    public Mono<ApiResponse<?>> viewProfile(Principal connectedUser) {
        var adminUser= ((Admin)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        LoggedInUserDetailDto loggedInUserDetailDto = profileMapper.getLoggedInUserDetailDto(adminUser);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(loggedInUserDetailDto,"profile details fetched successfully"));
    }
    @Override
    public Mono<ApiResponse<?>> changePassword(PasswordChangeRequest passwordChangeRequest, Principal connectedUser) {

        var admin= ((Admin) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());

        if (!passwordEncoder.matches(passwordChangeRequest.getOldPassword(),admin.getPassword())){
            return Mono.just(ResponseUtil.getFailureResponse("old password is incorrect"));
        }
        if(!passwordChangeRequest.getNewPassword().equals((passwordChangeRequest.getRetypeNewPassword()))){
            return Mono.just(ResponseUtil.getFailureResponse("new passwords do not match"));
        }
        else {
            admin.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
            admin.setPasswordChangeDate(new Date());
            adminRepository.save(admin);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("password changed successfully"));
        }
    }

    @Override
    @Transactional
    public Mono<ApiResponse<?>> editProfile(EditProfileRequest editProfileRequest, Principal connectedUser, MultipartFile image) throws IOException {
        Optional<Admin> adminByMoblieNumber = adminRepository.findByMobileNumber(editProfileRequest.getMobileNumber());
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(connectedUser.getName());
        if (adminByMoblieNumber.isPresent() && !adminByMoblieNumber.get().equals(optionalAdmin.get())){
            return Mono.just(ResponseUtil.getFailureResponse("Admin with given mobile number already exists"));
        }
        if (optionalAdmin.get().getStatus().equals(statusRepository.findByName(StatusConstant.ACTIVE.getName()))){
            Admin admin = profileMapper.mapToUpdateEntity(optionalAdmin.get(), editProfileRequest, image);
            adminRepository.save(admin);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Profile updated successfully"));
        }
        return Mono.just(ResponseUtil.getNotFoundResponse("Unsuccessful update of profile"));
    }
//
//    @Override
//    public Mono<ApiResponse<?>> sentEmailChangeLink(EmailChangeLinkRequest emailChangeLinkRequest, Principal connectedUser) {
//        Optional<Admin> adminByEmail = adminRepository.findByEmail(emailChangeLinkRequest.getEmail());
//        Optional<Admin> optionalAdmin = adminRepository.findByUsername(connectedUser.getName());
//        if (adminByEmail.isPresent()){
//            if (adminByEmail.get().equals(optionalAdmin.get())){
//                return Mono.just(ResponseUtil.getFailureResponse("You are already registered with that email"));
//            }
//            return Mono.just(ResponseUtil.getFailureResponse("Admin with given email already exists"));
//        }
//        try {
//            AdminEmailLog adminEmailLog = adminEmailLogMapper.mapToChangeEmail(optionalAdmin.get(), emailChangeLinkRequest);
//            SendEmailRequest sendEmailRequest = new SendEmailRequest();
//            sendEmailRequest.setRecipient(adminEmailLog.getEmail());
//            sendEmailRequest.setSubject(EmailSubjectConstant.EMAIL_VERIFICATION);
//            sendEmailRequest.setMessage(adminEmailLog.getMessage());
//            mailService.sendEmail(sendEmailRequest);
//            adminActivityService.logActivity(connectedUser, "Email Change", "Admin", adminEmailLog.getEmail());
//            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Email change request successful, Please verify your email!"));
//        }
//        catch (Exception e){
//            return Mono.just(ResponseUtil.getFailureResponse("Email change request failed!"));
//        }
//    }

//    @Override
//    public Mono<ApiResponse<?>> changeEmail(EmailChangeRequest emailChangeRequest) {
//        Optional<AdminEmailLog> adminEmailLog = adminEmailLogRepository.findByUuid(emailChangeRequest.getUuid());
//        if (adminEmailLog.isEmpty()) {
//            return Mono.just(ResponseUtil.getFailureResponse("Invalid user"));
//        } else {
//            AdminEmailLog existingAdminEmailLog = adminEmailLog.get();
//            if (existingAdminEmailLog.isExpired()) {
//                return Mono.just(ResponseUtil.getFailureResponse("Link expired"));
//            } else {
//                Optional<Admin> admin = adminRepository.findByEmail(existingAdminEmailLog.getAdmin().getEmail());
//                Admin existingAdmin = admin.get();
//                if (passwordEncoder.matches(emailChangeRequest.getPassword(), existingAdmin.getPassword())){
//                    existingAdmin.setEmail(adminEmailLog.get().getEmail());
//                    adminRepository.save(existingAdmin);
//                    return Mono.just(ResponseUtil.getSuccessfulApiResponse("Email changed successfully!"));
//                }
//                else {
//                    return Mono.just(ResponseUtil.getFailureResponse("Wrong password!"));
//                }
//            }
//        }
//    }
}
