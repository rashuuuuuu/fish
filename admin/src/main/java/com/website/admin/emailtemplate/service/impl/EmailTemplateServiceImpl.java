package com.website.admin.emailtemplate.service.impl;


import com.website.admin.emailtemplate.mapper.EmailTemplateMapper;
import com.website.admin.emailtemplate.model.CreateEmailTemplate;
import com.website.admin.emailtemplate.model.ViewEmailTemplateRequest;
import com.website.admin.emailtemplate.model.dto.EmailTemplateDto;
import com.website.admin.emailtemplate.service.EmailTemplateService;
import com.website.common.dto.ApiResponse;
import com.website.common.util.ResponseUtil;
import com.website.entity.EmailTemplate;
import com.website.repository.EmailTemplateRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class EmailTemplateServiceImpl implements EmailTemplateService {

    private final EmailTemplateRepository emailTemplateRepository;
    private final EmailTemplateMapper emailTemplateMapper;

    @Override
    @Transactional
    public Mono<ApiResponse> createEmailTemplate(@Valid CreateEmailTemplate createEmailTemplate, Principal connectedUser) {
        EmailTemplate emailTemplate = emailTemplateMapper.mapToEntity(createEmailTemplate);
        emailTemplateRepository.save(emailTemplate);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Email Template Created Successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> viewEmailTemplateList() {
        List<EmailTemplateDto> emailTemplateList = emailTemplateRepository.findAll()
                .stream()
                .map(emailTemplateMapper::entityToResponse)
                .collect(Collectors.toList());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(emailTemplateList, "Email templates fetched successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> viewEmailTemplateDetail(ViewEmailTemplateRequest viewEmailTemplateRequest) {
        Optional<EmailTemplate> emailTemplate = emailTemplateRepository.findByName(viewEmailTemplateRequest.getName());
        if(emailTemplate==null){
            Mono.just(ResponseUtil.getNotFoundResponse("Email template not found"));
        }
        EmailTemplateDto emailTemplateDto = emailTemplateMapper.viewTemplateDetail(emailTemplate.get());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(emailTemplateDto,"Email template fetched successfully"));
    }
}
