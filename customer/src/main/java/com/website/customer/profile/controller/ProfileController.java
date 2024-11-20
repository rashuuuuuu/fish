package com.website.customer.profile.controller;

import com.website.common.constant.ApiConstant;
import com.website.common.dto.ApiResponse;
import com.website.customer.profile.model.ChangePasswordRequest;
import com.website.customer.profile.model.EditProfileRequest;
import com.website.customer.profile.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.PROFILE)
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping(ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> getProfileDetail(Principal connectedUser){
        return profileService.getProfileDetail(connectedUser);
    }
    @PostMapping(ApiConstant.CHANGE_PASSWORD)
    public Mono<ApiResponse<?>> changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest, Principal connectedUser){
        return profileService.changePassword(changePasswordRequest, connectedUser);
    }
    @PostMapping(ApiConstant.UPDATE)
    public Mono<ApiResponse<?>> editProfile(@RequestBody @Valid EditProfileRequest editProfileRequest, Principal connectedUser){
        return profileService.editProfile(editProfileRequest, connectedUser);
    }
}
