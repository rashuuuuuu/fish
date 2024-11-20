package com.website.admin.status.controller;

import com.website.admin.status.service.StatusService;
import com.website.common.constant.ApiConstant;
import com.website.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.STATUS)
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @GetMapping
    public Mono<ApiResponse<?>> getStatusList(){
        return statusService.getAllStatus();
    }
}
