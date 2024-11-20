package com.website.customer.profile.service;


import com.website.common.dto.ApiResponse;
import com.website.customer.profile.model.ChangePasswordRequest;
import com.website.customer.profile.model.EditProfileRequest;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface ProfileService {
    Mono<ApiResponse<?>> getProfileDetail(Principal connectedUser);
    Mono<ApiResponse<?>> changePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser);
    Mono<ApiResponse<?>> editProfile(EditProfileRequest editProfileRequest, Principal connectedUser);
}
