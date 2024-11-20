package com.website.admin.emailtemplate.controller;

import com.website.common.constant.ApiConstant;
import com.website.common.dto.ApiResponse;
import com.website.admin.emailtemplate.model.CreateEmailTemplate;
import com.website.admin.emailtemplate.model.ViewEmailTemplateRequest;
import com.website.admin.emailtemplate.service.EmailTemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;
@RestController
@RequestMapping(ApiConstant.EMAIL_TEMPLATE)
@RequiredArgsConstructor
public class EmailTemplateController {
    private final EmailTemplateService emailTemplateService;
    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> createEmailTemplate(@RequestBody @Valid CreateEmailTemplate createEmailTemplate, Principal connectedUser){
        return emailTemplateService.createEmailTemplate(createEmailTemplate, connectedUser);
    }
    @GetMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> viewEmailTemplateList(){
        return emailTemplateService.viewEmailTemplateList();
    }
    @PostMapping(ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> viewEmailTemplateDetail(@Valid @RequestBody ViewEmailTemplateRequest viewEmailTemplateRequest){
        return emailTemplateService.viewEmailTemplateDetail(viewEmailTemplateRequest);
    }
}
