package com.website.admin.accessgroup.service;

import com.website.admin.accessgroup.model.BlockAccessGroupRequest;
import com.website.admin.accessgroup.model.CreateAccessGroupModel;
import com.website.admin.accessgroup.model.UnblockAccessGroupRequest;
import com.website.admin.accessgroup.model.request.DeleteAccessGroupRequest;
import com.website.admin.accessgroup.model.request.FetchAccessGroupDetail;
import com.website.admin.accessgroup.model.request.UpdateAccessGroupRequest;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.SearchParam;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface AccessGroupService {
    Mono<ApiResponse> createAccessGroup(CreateAccessGroupModel createAccessGroupModel,Principal connectedUser);
    Mono<ApiResponse<?>> updateAccessGroup(UpdateAccessGroupRequest request);
    Mono<ApiResponse<?>> deleteAccessGroup(DeleteAccessGroupRequest deleteAccessGroupRequest);
    Mono<ApiResponse<?>> getAllAccessGroup(SearchParam searchParam);
    Mono<ApiResponse<?>> getAccessGroupDetail(FetchAccessGroupDetail fetchAccessGroupDetail);
    Mono<ApiResponse<?>> blockAccessGroup(BlockAccessGroupRequest blockAccessGroupRequest, Principal connectedUser);
    Mono<ApiResponse<?>> unblockAccessGroup(UnblockAccessGroupRequest unblockAccessGroupRequest, Principal connectedUser);
}

