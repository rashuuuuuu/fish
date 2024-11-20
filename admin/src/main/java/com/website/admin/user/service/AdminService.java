package com.website.admin.user.service;
import com.website.admin.user.model.CreateAdminModel;
import com.website.admin.user.model.FetchAdminDetail;
import com.website.admin.user.model.request.*;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.SearchParam;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface AdminService {
    Mono<ApiResponse> createAdminUser(CreateAdminModel createAdminModel, Principal connectedUser);
    Mono<ApiResponse<?>> getAllAdminUsers(SearchParam searchParam);
    Mono<ApiResponse<?>> getAdminUserDetails(FetchAdminDetail fetchAdminDetail);
    Mono<ApiResponse<?>> updateAdminUser(UpdateAdminRequest updateAdminRequest);
    Mono<ApiResponse<?>> deleteAdminUser(DeleteAdminRequest deleteAdminRequest, Principal connectedUser);
    Mono<ApiResponse<?>> blockAdminUser(BlockAdminRequest blockAdminRequest, Principal connectedUser);
    Mono<ApiResponse<?>> unblockAdminUser(UnblockAdminUserRequest unblockAdminRequest, Principal connectedUser);
    Mono<ApiResponse<?>> createAdminPassword(CreatePasswordRequest createPasswordRequest);
    Mono<ApiResponse<?>> sendPasswordResetLink(PasswordResetLinkRequest passwordResetLinkRequest, Principal connectedUser);
    Mono<ApiResponse<?>> resetPassword(ResetPasswordRequest resetPasswordRequest);
}
