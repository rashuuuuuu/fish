package com.website.admin.accessgroup.mapper;

import com.website.admin.accessgroup.entity.AccessGroup;
import com.website.admin.accessgroup.model.AccessGroupDetailDto;
import com.website.admin.accessgroup.model.CreateAccessGroupModel;
import com.website.admin.accessgroup.model.SearchAccessGroupResponse;
import com.website.admin.accessgroup.model.request.UpdateAccessGroupRequest;
import com.website.admin.accessgroup.repo.AccessGroupRepository;
import com.website.admin.accessgroup.repo.AccessGroupRoleMapRepository;
import com.website.common.constant.StatusConstant;
import com.website.common.mapper.StatusMapper;
import com.website.common.repo.StatusRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AccessGroupMapper {
    @Autowired
    protected StatusRepository statusRepository;
    @Autowired
    protected StatusMapper statusMapper;
    @Autowired
    protected AccessGroupRepository accessGroupRepository;
    @Autowired
    protected AccessGroupRoleMapRepository accessGroupRoleMapRepository;

    public AccessGroup toEntity(CreateAccessGroupModel createAccessGroupModel) {
        AccessGroup accessGroup = new AccessGroup();
        accessGroup.setName(createAccessGroupModel.getName());
        accessGroup.setDescription(createAccessGroupModel.getDescription());
        accessGroup.setCreatedAt(new Date());
        accessGroup.setSuperAdminGroup(false);
        accessGroup.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
        return accessGroupRepository.save(accessGroup);
    }

    public abstract SearchAccessGroupResponse entityToResponse(AccessGroup accessGroup);

    public List<SearchAccessGroupResponse> getAccessGroupResponses(List<AccessGroup> accessGroups) {
        return accessGroups.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public abstract AccessGroupDetailDto getAccessGroupDetailDto(AccessGroup accessGroup);

    public AccessGroup updateAccessGroup(UpdateAccessGroupRequest request, AccessGroup accessGroup) {
        if (request == null || accessGroup == null) {
            return null;
        }
        accessGroup.setName(request.getName());
        accessGroup.setDescription(request.getDescription());
        accessGroup.setUpdatedAt(new Date());
        return accessGroup;
    }

}
