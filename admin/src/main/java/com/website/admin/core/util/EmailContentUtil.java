package com.website.admin.core.util;


import com.website.admin.core.constant.FreeMarkerTemplateConstant;
import com.website.admin.core.model.PrepareEmailContentDto;
import com.website.entity.EmailTemplate;
import com.website.repository.EmailTemplateRepository;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmailContentUtil {
    @Autowired
    private EmailTemplateRepository emailTemplateRepository;
    @Autowired
    private freemarker.template.Configuration freeMarkerConfig;

    public String prepareEmailContent(PrepareEmailContentDto prepareEmailContentDto){
        EmailTemplate emailTemplate = emailTemplateRepository.findEmailTemplateByName(prepareEmailContentDto.getTemplateName());
        Map<String, Object> model = new HashMap<>();
        model.put(FreeMarkerTemplateConstant.USERNAME, prepareEmailContentDto.getAdminUserName());
        model.put(FreeMarkerTemplateConstant.EXPIRATION_TIME, prepareEmailContentDto.getExpirationTime());
        model.put(FreeMarkerTemplateConstant.VERIFICATION_LINK,"http://46.250.248.179/setPassword/"+prepareEmailContentDto.getUuid());
        model.put(FreeMarkerTemplateConstant.CURRENT_YEAR, Year.now().getValue());

        String emailContent;

        try{
            Template template = new Template("emailTemplate", emailTemplate.getTemplate(), freeMarkerConfig);
            emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        }catch (Exception e) {
            throw new RuntimeException("Error processing email template: "+ e.getMessage());
        }

        return emailContent;

    }

    public String preparePasswordResetEmailContent(PrepareEmailContentDto prepareEmailContentDto){
        EmailTemplate emailTemplate = emailTemplateRepository.findEmailTemplateByName(prepareEmailContentDto.getTemplateName());
        Map<String, Object> model = new HashMap<>();
        model.put(FreeMarkerTemplateConstant.USERNAME, prepareEmailContentDto.getAdminUserName());
        model.put(FreeMarkerTemplateConstant.EXPIRATION_TIME, prepareEmailContentDto.getExpirationTime());
        model.put(FreeMarkerTemplateConstant.VERIFICATION_LINK,"http://46.250.248.179/resetPassword/"+prepareEmailContentDto.getUuid());
        model.put(FreeMarkerTemplateConstant.CURRENT_YEAR, Year.now().getValue());

        String emailContent;

        try{
            Template template = new Template("emailTemplate", emailTemplate.getTemplate(), freeMarkerConfig);
            emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        }catch (Exception e) {
            throw new RuntimeException("Error processing email template: "+ e.getMessage());
        }

        return emailContent;

    }

    public String prepareEmailChangeEmailContent(PrepareEmailContentDto prepareEmailContentDto){
        EmailTemplate emailTemplate = emailTemplateRepository.findEmailTemplateByName(prepareEmailContentDto.getTemplateName());
        Map<String, Object> model = new HashMap<>();
        model.put(FreeMarkerTemplateConstant.USERNAME, prepareEmailContentDto.getAdminUserName());
        model.put(FreeMarkerTemplateConstant.EXPIRATION_TIME, prepareEmailContentDto.getExpirationTime());
        model.put(FreeMarkerTemplateConstant.VERIFICATION_LINK,"http://46.250.248.179/setEmail/"+prepareEmailContentDto.getUuid());
        model.put(FreeMarkerTemplateConstant.CURRENT_YEAR, Year.now().getValue());

        String emailContent;

        try{
            Template template = new Template("emailTemplate", emailTemplate.getTemplate(), freeMarkerConfig);
            emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        }catch (Exception e) {
            throw new RuntimeException("Error processing email template: "+ e.getMessage());
        }

        return emailContent;

    }
}
