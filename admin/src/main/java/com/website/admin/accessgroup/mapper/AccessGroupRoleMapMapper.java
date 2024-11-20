package com.website.admin.accessgroup.mapper;

import com.website.admin.accessgroup.entity.AccessGroup;
import com.website.admin.accessgroup.entity.AccessGroupRoleMap;
import com.website.admin.accessgroup.model.AssignRoleModel;
import com.website.admin.accessgroup.repo.AccessGroupRoleMapRepository;
import com.website.admin.role.entity.Roles;
import com.website.admin.role.repository.RolesRepository;
import com.website.admin.role.service.RolesService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AccessGroupRoleMapMapper {
    @Autowired
    protected RolesService rolesService;
    @Autowired
    AccessGroupRoleMapRepository accessGroupRoleMapRepository;
    @Autowired
    protected RolesRepository rolesRepository;

    public List<AccessGroupRoleMap> createAccessGroupRoleMap(AccessGroup accessGroup, List<AssignRoleModel> roles) {
        List<Long> assignedRoleId = roles.stream().map(AssignRoleModel::getRoleId).toList();
        List<Roles> allRoles = rolesService.getAllRoles();
        List<AccessGroupRoleMap> accessGroupRoleMaps = allRoles.stream().map(role -> {
            AccessGroupRoleMap accessGroupRoleMap = new AccessGroupRoleMap();
            accessGroupRoleMap.setAccessGroup(accessGroup);
            accessGroupRoleMap.setIsActive(assignedRoleId.contains(role.getId()));
            accessGroupRoleMap.setRoles(role);
            return accessGroupRoleMap;
        }).collect(Collectors.toList());

        return accessGroupRoleMapRepository.saveAll(accessGroupRoleMaps);
    }
    public List<AccessGroupRoleMap> updateAccessGroupRoleMap(AccessGroup accessGroup, List<AssignRoleModel> roles) {
        List<Long> assignedRoleId = roles.stream().map(AssignRoleModel::getRoleId).toList();
        List<AccessGroupRoleMap> existingRoleMaps= accessGroupRoleMapRepository.findByAccessGroup(accessGroup);

        Map<Long, AccessGroupRoleMap> existingRoleMapById = existingRoleMaps.stream()
                .collect(Collectors.toMap(roleMap -> roleMap.getRoles().getId(), roleMap -> roleMap));

        List<AccessGroupRoleMap> updatedRoleMaps = new ArrayList<>();
        for (Long roleId : assignedRoleId) {
            AccessGroupRoleMap roleMap = existingRoleMapById.get(roleId);
            if (roleMap != null) {
                roleMap.setIsActive(true);
                updatedRoleMaps.add(roleMap);
            } else {
                Roles role = rolesRepository.findById(roleId).orElseThrow();
                AccessGroupRoleMap newRoleMap = new AccessGroupRoleMap();
                newRoleMap.setAccessGroup(accessGroup);
                newRoleMap.setRoles(role);
                newRoleMap.setIsActive(true);
                updatedRoleMaps.add(newRoleMap);
            }
        }
        existingRoleMaps.stream()
                .filter(roleMap -> !updatedRoleMaps.contains(roleMap))
                .forEach(updatedRoleMaps::add);

        return accessGroupRoleMapRepository.saveAll(updatedRoleMaps);

    }

}
