package com.website.admin.role.service.impl;

import com.website.common.dto.ApiResponse;
import com.website.common.util.ResponseUtil;
import com.website.admin.role.entity.Roles;
import com.website.admin.role.mapper.GroupRoleBuilder;
import com.website.admin.role.model.RolesResponse;
import com.website.admin.role.repository.RolesRepository;
import com.website.admin.role.service.RolesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RolesServiceImpl implements RolesService {
    private final RolesRepository rolesRepository;
    private final GroupRoleBuilder groupRoleBuilder;

    @Override
    public List<Roles> getAllRoles() {
        return rolesRepository.getAllRoles();
    }

    @Override
    public Mono<ApiResponse<?>> getAllAvailableRoles() {
        List<Roles> rolesList = rolesRepository.findAll();
        List<RolesResponse> data = groupRoleBuilder.buildRolesResponse(rolesList, new ArrayList<>());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(data, "Navigation fetched succesfully."));
    }
}
