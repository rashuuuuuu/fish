package com.website.admin.emailtemplate.service;


import com.website.admin.emailtemplate.model.CreateEmailTemplate;
import com.website.admin.emailtemplate.model.ViewEmailTemplateRequest;
import com.website.common.dto.ApiResponse;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface EmailTemplateService {
    Mono<ApiResponse> createEmailTemplate(CreateEmailTemplate createEmailTemplate, Principal connectedUser);
    Mono<ApiResponse<?>> viewEmailTemplateList();
    Mono<ApiResponse<?>> viewEmailTemplateDetail(ViewEmailTemplateRequest viewEmailTemplateRequest);
}
