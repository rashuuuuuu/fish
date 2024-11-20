package com.website.admin.accessgroup.controller;

import com.website.admin.accessgroup.model.BlockAccessGroupRequest;
import com.website.admin.accessgroup.model.CreateAccessGroupModel;
import com.website.admin.accessgroup.model.UnblockAccessGroupRequest;
import com.website.admin.accessgroup.model.request.DeleteAccessGroupRequest;
import com.website.admin.accessgroup.model.request.FetchAccessGroupDetail;
import com.website.admin.accessgroup.model.request.UpdateAccessGroupRequest;
import com.website.admin.accessgroup.service.AccessGroupService;
import com.website.common.constant.ApiConstant;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.SearchParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.ACCESS_GROUP)
@RequiredArgsConstructor
public class AccessGroupController {
    private final AccessGroupService accessGroupService;


    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> addAccessGroup(@RequestBody @Valid CreateAccessGroupModel createAccessGroupModel,
                                            Principal connectedUser) {
        return accessGroupService.createAccessGroup(createAccessGroupModel, connectedUser);
    }

    @PostMapping()
    public Mono<ApiResponse<?>> getAllAccessGroups(@RequestBody @Valid SearchParam searchParam) {
        return accessGroupService.getAllAccessGroup(searchParam);
    }

    @PostMapping(ApiConstant.GET + ApiConstant.SLASH + ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> getAccessGroupDetail(@RequestBody @Valid FetchAccessGroupDetail fetchAccessGroupDetail) {
        return accessGroupService.getAccessGroupDetail(fetchAccessGroupDetail);
    }
    @PostMapping(ApiConstant.UPDATE)
    public Mono<ApiResponse<?>> updateAccessGroup(@RequestBody @Valid UpdateAccessGroupRequest request){
        return accessGroupService.updateAccessGroup(request);

    }
    @PostMapping(ApiConstant.DELETE)
    public Mono<ApiResponse<?>> deleteAccessGroup(@RequestBody @Valid DeleteAccessGroupRequest request){
        return accessGroupService.deleteAccessGroup(request);
    }
    @PostMapping(ApiConstant.BLOCK)
    public Mono<ApiResponse<?>> blockAccessGroup(@RequestBody @Valid BlockAccessGroupRequest blockAccessGroupRequest, Principal connectedUser){
        return accessGroupService.blockAccessGroup(blockAccessGroupRequest, connectedUser);
    }
    @PostMapping(ApiConstant.UNBLOCK)
    public Mono<ApiResponse<?>> unblockAccessGroup(@RequestBody @Valid UnblockAccessGroupRequest unblockAccessGroupRequest, Principal connectedUser){
        return accessGroupService.unblockAccessGroup(unblockAccessGroupRequest, connectedUser);
    }
}
