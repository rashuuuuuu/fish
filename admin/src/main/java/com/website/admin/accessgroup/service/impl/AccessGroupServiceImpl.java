package com.website.admin.accessgroup.service.impl;

import com.website.admin.accessgroup.entity.AccessGroup;
import com.website.admin.accessgroup.mapper.AccessGroupMapper;
import com.website.admin.accessgroup.mapper.AccessGroupRoleMapMapper;
import com.website.admin.accessgroup.model.*;
import com.website.admin.accessgroup.model.request.DeleteAccessGroupRequest;
import com.website.admin.accessgroup.model.request.FetchAccessGroupDetail;
import com.website.admin.accessgroup.model.request.UpdateAccessGroupRequest;
import com.website.admin.accessgroup.repo.AccessGroupRepository;
import com.website.admin.accessgroup.repo.AccessGroupSearchRepository;
import com.website.admin.accessgroup.service.AccessGroupService;
import com.website.admin.adminActivityLog.entity.AdminActivityLog;
import com.website.admin.adminActivityLog.service.AdminActivityService;
import com.website.common.constant.StatusConstant;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.PageableResponse;
import com.website.common.dto.SearchParam;
import com.website.common.dto.SearchResponseWithMapperBuilder;
import com.website.common.repo.StatusRepository;
import com.website.common.service.SearchResponse;
import com.website.common.util.ResponseUtil;
import com.website.admin.log.entity.AccessGroupBlockLog;
import com.website.admin.log.entity.AccessGroupUnblockLog;
import com.website.admin.log.mapper.AccessGroupLogMapper;
import com.website.admin.user.repo.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessGroupServiceImpl implements AccessGroupService {
    private final AccessGroupRepository accessGroupRepository;
    private final AccessGroupMapper accessGroupMapper;
    private final AccessGroupRoleMapMapper accessGroupRoleMapMapper;
    private final SearchResponse searchResponse;
    private final AccessGroupSearchRepository accessGroupSearchRepository;
    private final StatusRepository statusRepository;
    private final AccessGroupLogMapper accessGroupLogMapper;
    private final AdminActivityService adminActivityService;
    private final AdminRepository adminRepository;


    @Override
    @Transactional
    public Mono<ApiResponse> createAccessGroup(CreateAccessGroupModel createAccessGroupModel,Principal connectedUser) {
        Optional<AccessGroup> existedAccessGroup = accessGroupRepository.findByName(createAccessGroupModel.getName());
        if (existedAccessGroup.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("This name is already taken. Please use different name."));
        }
        AccessGroup accessGroup = accessGroupMapper.toEntity(createAccessGroupModel);
        adminActivityService.logActivity(connectedUser, "Create", "AccessGroup", accessGroup.getName());
        accessGroupRoleMapMapper.createAccessGroupRoleMap(accessGroup, createAccessGroupModel.getRoles());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Access group created"));
    }
    @Override
    @Transactional
    public Mono<ApiResponse<?>> updateAccessGroup(UpdateAccessGroupRequest request) {
        Optional<AccessGroup> existedAccessGroup = accessGroupRepository.findByName(request.getName());
        if (existedAccessGroup.isPresent() && !existedAccessGroup.get().getName().equals(request.getName())) {
            return Mono.just(ResponseUtil.getFailureResponse("Access group name already exists"));
        }
        if (existedAccessGroup.isPresent()) {
            AccessGroup updatedAccessGroup = accessGroupMapper.updateAccessGroup(request, existedAccessGroup.get());
            accessGroupRepository.save(updatedAccessGroup);
            accessGroupRoleMapMapper.updateAccessGroupRoleMap(updatedAccessGroup, request.getRoles());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Access group updated successfully"));
        } else {
            return Mono.just(ResponseUtil.getNotFoundResponse("Access group not found"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> deleteAccessGroup(DeleteAccessGroupRequest deleteAccessGroupRequest) {
        Optional<AccessGroup> accessGroup = accessGroupRepository.findByName(deleteAccessGroupRequest.getName());
        if (accessGroup.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Access group not found"));
        } else {
            AccessGroup accessGroup1 = accessGroup.get();
            if ("DELETED".equals(accessGroup1.getStatus().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Access group not found"));
            }
            accessGroup1.setStatus(statusRepository.findByName("DELETED"));
            accessGroupRepository.save(accessGroup1);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Access group deleted successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> getAllAccessGroup(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<AccessGroup, SearchAccessGroupResponse> responseBuilder = SearchResponseWithMapperBuilder
                .<AccessGroup, SearchAccessGroupResponse>builder()
                .count(accessGroupSearchRepository::count)
                .searchData(accessGroupSearchRepository::getAll)
                .mapperFunction(this.accessGroupMapper::getAccessGroupResponses)
                .searchParam(searchParam)
                .build();
        PageableResponse<SearchAccessGroupResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Access groups fetched successfully."));
    }
    @Override
    public Mono<ApiResponse<?>> getAccessGroupDetail(FetchAccessGroupDetail fetchAccessGroupDetail) {
        Optional<AccessGroup> accessGroup = accessGroupRepository.findByName(fetchAccessGroupDetail.getName());
        if (accessGroup.isEmpty()) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Access group  not found"));
        } else {
            AccessGroupDetailDto accessGroupDetailDto = accessGroupMapper.getAccessGroupDetailDto(accessGroup.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(accessGroupDetailDto, "Access group fetched successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> blockAccessGroup(BlockAccessGroupRequest blockAccessGroupRequest, Principal connectedUSer) {
        Optional<AccessGroup> optionalAccessGroup = accessGroupRepository.findByName(blockAccessGroupRequest.getName());
        if (optionalAccessGroup.isEmpty() || StatusConstant.DELETED.getName().equals(optionalAccessGroup.get().getStatus().getName())){
            return Mono.just(ResponseUtil.getNotFoundResponse("AccessGroup not found"));
        }
        else if(StatusConstant.BLOCKED.getName().equals(optionalAccessGroup.get().getStatus().getName())){
            return Mono.just(ResponseUtil.getFailureResponse("AccessGroup is already blocked"));
        }
        AccessGroup accessGroup = optionalAccessGroup.get();
        accessGroup.setStatus(statusRepository.findByName(StatusConstant.BLOCKED.getName()));
        AccessGroupBlockLog accessGroupBlockLog = accessGroupLogMapper.mapToBlock(accessGroup, blockAccessGroupRequest, connectedUSer);
        AdminActivityLog adminActivityLog = adminActivityService.logActivity(connectedUSer,
                "Block", "AccessGroup", accessGroup.getName());
        if (adminActivityLog!=null || accessGroupBlockLog!=null){
            accessGroupRepository.save(accessGroup);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("AccessGroup blocked successfully"));
        }
        return Mono.just(ResponseUtil.getFailureResponse("Access group not blocked"));
    }

    @Override
    public Mono<ApiResponse<?>> unblockAccessGroup(UnblockAccessGroupRequest unblockAccessGroupRequest, Principal connectedUser) {
        Optional<AccessGroup> optionalAccessGroup = accessGroupRepository.findByName(unblockAccessGroupRequest.getName());
        if (optionalAccessGroup.isEmpty() || StatusConstant.DELETED.getName().equals(optionalAccessGroup.get().getStatus().getName())){
            return Mono.just(ResponseUtil.getNotFoundResponse("AccessGroup not found"));
        }
        else if(StatusConstant.BLOCKED.getName().equals(optionalAccessGroup.get().getStatus().getName())){
            AccessGroup accessGroup = optionalAccessGroup.get();
            accessGroup.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
            accessGroupRepository.save(accessGroup);

            AccessGroupUnblockLog accessGroupUnblockLog = accessGroupLogMapper.mapToUnblock(accessGroup, unblockAccessGroupRequest, connectedUser);
            AdminActivityLog adminActivityLog = adminActivityService.logActivity(connectedUser,
                    "Unblock", "AccessGroup", accessGroup.getName());
            if (adminActivityLog!=null || accessGroupUnblockLog!=null){
                accessGroupRepository.save(accessGroup);
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("AccessGroup unblocked successfully"));
            }
        }
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("AccessGroup cannot be unblock"));
    }
}
