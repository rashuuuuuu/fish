package com.website.admin.user.mapper;

import com.website.admin.accessgroup.repo.AccessGroupRepository;
import com.website.admin.core.constant.EmailSubjectConstant;
import com.website.admin.log.entity.AdminEmailLog;
import com.website.admin.log.mapper.AdminEmailLogMapper;
import com.website.admin.user.entity.Admin;
import com.website.admin.user.model.AdminUserDetailDto;
import com.website.admin.user.model.CreateAdminModel;
import com.website.admin.user.model.SearchAdminUserResponse;
import com.website.admin.user.model.request.UpdateAdminRequest;
import com.website.admin.user.repo.AdminRepository;
import com.website.common.constant.StatusConstant;
import com.website.common.dto.request.SendEmailRequest;
import com.website.common.repo.StatusRepository;
import com.website.common.service.MailService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AdminMapper {

    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected AccessGroupRepository accessGroupRepository;
    @Autowired
    protected AdminRepository adminRepository;
    @Autowired
    private AdminEmailLogMapper adminEmailLogMapper;
    @Autowired
    protected MailService mailService;
    public Admin mapToEntity(CreateAdminModel createAdminModel) {
        Admin admin = new Admin();

        admin.setName(createAdminModel.getName());
        admin.setMobileNumber(createAdminModel.getMobileNumber());
        admin.setAddress(createAdminModel.getAddress());
        admin.setEmail(createAdminModel.getEmail());
        admin.setAccessGroup(accessGroupRepository.findByName(createAdminModel.getAccessGroup().getName()).orElseThrow());
        admin.setUsername(createAdminModel.getEmail());
        admin.setActive(false);
        admin.setCode(UUID.randomUUID().toString());
        admin.setCreatedAt(new Date());
        admin.setStatus(statusRepository.findByName(StatusConstant.PENDING.getName()));
        admin.setSuperAdmin(false);
        Admin savedAdmin =  adminRepository.save(admin);

        AdminEmailLog adminEmailLog = adminEmailLogMapper.mapToEntity(savedAdmin);
        SendEmailRequest sendEmailRequest = new SendEmailRequest();
        sendEmailRequest.setRecipient(savedAdmin.getEmail());
        sendEmailRequest.setSubject(EmailSubjectConstant.EMAIL_VERIFICATION);
        sendEmailRequest.setMessage(adminEmailLog.getMessage());
        mailService.sendEmail(sendEmailRequest);
        return admin;
    }
    public Admin updateAdminUser(UpdateAdminRequest request, Admin admin) {
        admin.setName(request.getName());
        admin.setMobileNumber(request.getMobileNumber());
        admin.setAddress(request.getAddress());
        admin.setUpdatedAt(new Date());
        admin.setAccessGroup(accessGroupRepository.findByName(request.getAccessGroup().getName()).orElseThrow());
        return admin;
    }

    public abstract SearchAdminUserResponse entityToResponse(Admin admin);

    public List<SearchAdminUserResponse> getAdminUserResponses(List<Admin> admins) {
        return admins.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public abstract AdminUserDetailDto getAdminUserDetails(Admin admin);

}
