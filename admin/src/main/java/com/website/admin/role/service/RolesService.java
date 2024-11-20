package com.website.admin.role.service;

import com.website.common.dto.ApiResponse;
import com.website.admin.role.entity.Roles;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RolesService {
    List<Roles> getAllRoles();

    Mono<ApiResponse<?>> getAllAvailableRoles();
}
