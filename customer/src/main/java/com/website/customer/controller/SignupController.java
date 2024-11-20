package com.website.customer.controller;

import com.website.common.constant.ApiConstant;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.SignUpModel;
import com.website.customer.service.SignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = ApiConstant.SIGNUP)
@RequiredArgsConstructor
public class SignupController {
    private final SignupService signupService;

    @PostMapping()
    public Mono<ApiResponse<?>> signUp(@RequestBody @Valid SignUpModel signUpModel){
        return signupService.signup(signUpModel);
    }

}
