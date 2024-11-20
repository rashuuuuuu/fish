package com.website.admin.user.controller;
import com.website.admin.user.model.CreateAdminModel;
import com.website.admin.user.model.FetchAdminDetail;
import com.website.admin.user.model.request.*;
import com.website.admin.user.service.AdminService;
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
@RequestMapping(ApiConstant.ADMIN)
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> createAdminUser(@RequestBody @Valid CreateAdminModel createAdminModel,
                                             Principal connectedUser){
        return adminService.createAdminUser(createAdminModel, connectedUser);
    }
    @PostMapping()
    public Mono<ApiResponse<?>> getAllAdminUsers(@RequestBody @Valid SearchParam searchParam){
        return adminService.getAllAdminUsers(searchParam);
    }
    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> getAdminUserDetails(@RequestBody @Valid FetchAdminDetail fetchAdminDetail){
        return adminService.getAdminUserDetails(fetchAdminDetail);
    }
    @PostMapping(ApiConstant.UPDATE)
    public Mono<ApiResponse<?>> updateAdmin(@RequestBody @Valid UpdateAdminRequest request){
        return adminService.updateAdminUser(request);
    }
    @PostMapping(ApiConstant.DELETE)
    public Mono<ApiResponse<?>> deleteAdminUser(@RequestBody @Valid DeleteAdminRequest request, Principal connectedUser){
        return adminService.deleteAdminUser(request, connectedUser);
    }
    @PostMapping(ApiConstant.BLOCK)
    public Mono<ApiResponse<?>> blockAdminUser(@RequestBody@Valid BlockAdminRequest request, Principal connectedUser){
        return adminService.blockAdminUser(request,connectedUser);
    }
    @PostMapping(ApiConstant.UNBLOCK)
    public Mono<ApiResponse<?>> unblockAdminUser(@RequestBody @Valid UnblockAdminUserRequest request, Principal connectedUser){
        return adminService.unblockAdminUser(request, connectedUser);
    }
    @PostMapping(ApiConstant.SET_PASSWORD)
    public Mono<ApiResponse<?>> setPassword(@RequestBody @Valid CreatePasswordRequest createPasswordRequest){
        return adminService.createAdminPassword(createPasswordRequest);
    }
    @PostMapping(ApiConstant.SENT_RESET_PASSWORD_LINK)
    public Mono<ApiResponse<?>> sentResetPasswordLink(@RequestBody @Valid PasswordResetLinkRequest passwordResetLinkRequest,
                                              Principal connectedUser){
        return adminService.sendPasswordResetLink(passwordResetLinkRequest, connectedUser);
    }

    @PostMapping(ApiConstant.RESET_PASSWORD)
    public Mono<ApiResponse<?>> resetPassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {
        return adminService.resetPassword(resetPasswordRequest);
    }

}
