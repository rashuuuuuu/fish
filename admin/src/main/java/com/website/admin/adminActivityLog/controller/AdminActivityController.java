package com.website.admin.adminActivityLog.controller;

import com.website.admin.adminActivityLog.service.AdminActivityService;
import com.website.common.constant.ApiConstant;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.SearchParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.ADMINACTIVITYLOG)
@RequiredArgsConstructor
public class AdminActivityController {
    private final AdminActivityService adminActivityService;
    @GetMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> getActivityLogs(Principal connectedUser){
        return adminActivityService.getAdminActivityLogs(connectedUser);
    }
    @PostMapping(ApiConstant.GETALL)
    public Mono<ApiResponse<?>> getAllActivityLogs(@RequestBody @Valid SearchParam searchParam, Principal connectedUser){
        return adminActivityService.getAllActivityLog(searchParam, connectedUser);
    }

    @PostMapping(ApiConstant.GET)
    private Mono<ApiResponse<?>> getActivityLog(@RequestBody @Valid SearchParam searchParam, Principal connectedUser){
        return adminActivityService.getAdminActivityLog(searchParam, connectedUser);
    }
}
