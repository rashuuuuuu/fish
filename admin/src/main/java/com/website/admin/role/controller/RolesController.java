package com.website.admin.role.controller;

import com.website.common.constant.ApiConstant;
import com.website.common.dto.ApiResponse;
import com.website.admin.role.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.ROLES)
@RequiredArgsConstructor
public class RolesController {
    private final RolesService rolesService;

    @GetMapping
    public Mono<ApiResponse<?>> getAllRoles() {
        return rolesService.getAllAvailableRoles();
    }
}
