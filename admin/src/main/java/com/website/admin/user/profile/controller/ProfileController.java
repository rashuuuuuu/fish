package com.website.admin.user.profile.controller;

import com.website.admin.user.profile.model.EditProfileRequest;
//import com.website.admin.user.profile.model.EmailChangeLinkRequest;
//import com.website.admin.user.profile.model.EmailChangeRequest;
import com.website.admin.user.profile.model.PasswordChangeRequest;
import com.website.admin.user.profile.service.ProfileService;
import com.website.common.constant.ApiConstant;
import com.website.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.ADMIN)
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping(ApiConstant.VIEW_PROFILE)
    public Mono<ApiResponse<?>> viewProfile(
            Principal connectedUser
    ){
        return profileService.viewProfile(connectedUser);
    }
    @PostMapping(ApiConstant.CHANGE_PASSWORD)
    public Mono<ApiResponse<?>> changePassword(
            @RequestBody @Valid PasswordChangeRequest passwordChangeRequest,
            Principal connectedUser
    ){
        return profileService.changePassword(passwordChangeRequest, connectedUser);
    }

    @PostMapping(value = ApiConstant.UPDATE_PROFILE, consumes = {"multipart/form-data"})
    public Mono<ApiResponse<?>> updateProfile(@RequestPart("profile") @Valid EditProfileRequest editProfileRequest,
                                              @RequestPart("image")MultipartFile image,
                                              Principal connectedUser) throws IOException{
        return profileService.editProfile(editProfileRequest, connectedUser, image);
    }

//    @PostMapping(ApiConstant.SEND_EMAIL_CHANGE_LINK)
//    public Mono<ApiResponse<?>> sendEmailChangeLink(@RequestBody @Valid EmailChangeLinkRequest emailChangeLinkRequest,
//                                            Principal connectedUser){
//        return profileService.sentEmailChangeLink(emailChangeLinkRequest, connectedUser);
//    }
//
//    @PostMapping(ApiConstant.CHANGE_EMAIL)
//    public Mono<ApiResponse<?>> changEmail(@RequestBody @Valid EmailChangeRequest emailChangeRequest){
//        return profileService.changeEmail(emailChangeRequest);
//    }

}
