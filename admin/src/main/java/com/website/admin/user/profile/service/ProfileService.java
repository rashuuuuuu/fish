package com.website.admin.user.profile.service;

import com.website.admin.user.profile.model.EditProfileRequest;
//import com.website.admin.user.profile.model.EmailChangeLinkRequest;
//import com.website.admin.user.profile.model.EmailChangeRequest;
import com.website.admin.user.profile.model.PasswordChangeRequest;
import com.website.common.dto.ApiResponse;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.Principal;

public interface ProfileService {
    Mono<ApiResponse<?>> viewProfile(Principal connectedUser);
    Mono<ApiResponse<?>> changePassword(PasswordChangeRequest passwordChangeRequest, Principal connectedUser);
    Mono<ApiResponse<?>> editProfile(EditProfileRequest editProfileRequest, Principal connectedUser, MultipartFile image) throws IOException;
//    Mono<ApiResponse<?>> sentEmailChangeLink(EmailChangeLinkRequest emailChangeLinkRequest, Principal connectedUser);
//    Mono<ApiResponse<?>> changeEmail(EmailChangeRequest emailChangeRequest);

}
