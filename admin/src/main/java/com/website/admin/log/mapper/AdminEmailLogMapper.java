package com.website.admin.log.mapper;


import com.website.admin.core.constant.EmailTemplateConstant;
import com.website.admin.core.model.PrepareEmailContentDto;
import com.website.admin.core.util.EmailContentUtil;
import com.website.admin.core.util.ExpirationTimeUtil;
import com.website.admin.core.util.UuidUtil;
import com.website.admin.log.entity.AdminEmailLog;
import com.website.admin.log.repo.AdminEmailLogRepository;
import com.website.repository.EmailTemplateRepository;
import com.website.admin.user.entity.Admin;
import com.website.admin.user.profile.model.EmailChangeLinkRequest;
import com.website.admin.user.repo.AdminRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AdminEmailLogMapper {

    @Autowired
    protected AdminEmailLogRepository adminEmailLogRepository;
    @Autowired
    protected AdminRepository adminRepository;
    @Autowired
    private freemarker.template.Configuration freeMarkerConfig;
    @Autowired
    protected EmailTemplateRepository emailTemplateRepository;
    @Autowired
    private EmailContentUtil emailContentUtil;

    public AdminEmailLog mapToEntity(Admin admin) {
        Date expirationTime = ExpirationTimeUtil.calculateExpirationTime();
        String uuid = UuidUtil.generateUuid();

        PrepareEmailContentDto prepareEmailContentDto = new PrepareEmailContentDto();
        prepareEmailContentDto.setAdminUserName(admin.getName());
        prepareEmailContentDto.setTemplateName(EmailTemplateConstant.ADMIN_MAIL_VERIFICATION);
        prepareEmailContentDto.setUuid(uuid);
        prepareEmailContentDto.setExpirationTime(expirationTime);
        String emailContent = emailContentUtil.prepareEmailContent(prepareEmailContentDto);

        AdminEmailLog adminEmailLog = new AdminEmailLog();
        adminEmailLog.setEmail(admin.getEmail());
        adminEmailLog.setAdmin(admin);
        adminEmailLog.setMessage(emailContent);
        adminEmailLog.setUuid(uuid);
        adminEmailLog.setExpired(false);
        adminEmailLogRepository.save(adminEmailLog);
        return adminEmailLog;
    }

    public AdminEmailLog mapToResetPasswordEntity(Admin admin) {
        Date expirationTime = ExpirationTimeUtil.calculateExpirationTime();
        String uuid = UuidUtil.generateUuid();

        PrepareEmailContentDto prepareEmailContentDto = new PrepareEmailContentDto();
        prepareEmailContentDto.setAdminUserName(admin.getName());
        prepareEmailContentDto.setTemplateName(EmailTemplateConstant.RESET_PASSWORD);
        prepareEmailContentDto.setUuid(uuid);
        prepareEmailContentDto.setExpirationTime(expirationTime);
        String emailContent = emailContentUtil.preparePasswordResetEmailContent(prepareEmailContentDto);

        AdminEmailLog adminEmailLog = new AdminEmailLog();
        adminEmailLog.setEmail(admin.getEmail());
        adminEmailLog.setAdmin(admin);
        adminEmailLog.setMessage(emailContent);
        adminEmailLog.setUuid(uuid);
        adminEmailLog.setExpired(false);
        adminEmailLogRepository.save(adminEmailLog);
        return adminEmailLog;
    }

    public AdminEmailLog mapToChangeEmail(Admin admin, EmailChangeLinkRequest emailChangeLinkRequest) {
        Date expirationTime = ExpirationTimeUtil.calculateExpirationTime();
        String uuid = UuidUtil.generateUuid();

        PrepareEmailContentDto prepareEmailContentDto = new PrepareEmailContentDto();
        prepareEmailContentDto.setAdminUserName(admin.getName());
        prepareEmailContentDto.setTemplateName(EmailTemplateConstant.ADMIN_EMAIL_CHANGE);
        prepareEmailContentDto.setUuid(uuid);
        prepareEmailContentDto.setExpirationTime(expirationTime);
        String emailContent = emailContentUtil.prepareEmailChangeEmailContent(prepareEmailContentDto);

        AdminEmailLog adminEmailLog = new AdminEmailLog();
        adminEmailLog.setEmail(emailChangeLinkRequest.getEmail());
        adminEmailLog.setAdmin(admin);
        adminEmailLog.setMessage(emailContent);
        adminEmailLog.setUuid(uuid);
        adminEmailLog.setExpired(false);
        adminEmailLogRepository.save(adminEmailLog);
        return adminEmailLog;
    }
}
