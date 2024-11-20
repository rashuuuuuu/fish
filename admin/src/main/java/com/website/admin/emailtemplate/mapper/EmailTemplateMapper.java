package com.website.admin.emailtemplate.mapper;


import com.website.admin.emailtemplate.model.CreateEmailTemplate;
import com.website.admin.emailtemplate.model.dto.EmailTemplateDto;
import com.website.entity.EmailTemplate;
import com.website.repository.EmailTemplateRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class EmailTemplateMapper {

    @Autowired
    protected EmailTemplateRepository emailTemplateRepository;

    public EmailTemplate mapToEntity(CreateEmailTemplate createEmailTemplate){
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setName(createEmailTemplate.getName());
        emailTemplate.setTemplate(createEmailTemplate.getTemplate());
        emailTemplate.setCreatedDate(new Date());
        return emailTemplate;
    }
    public abstract EmailTemplateDto entityToResponse(EmailTemplate emailTemplate);
    public abstract EmailTemplateDto viewTemplateDetail(EmailTemplate emailTemplate);
}
