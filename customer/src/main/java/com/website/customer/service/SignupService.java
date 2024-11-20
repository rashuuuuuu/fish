package com.website.customer.service;

import com.website.common.dto.ApiResponse;
import com.website.common.dto.SignUpModel;
import reactor.core.publisher.Mono;

public interface SignupService {
    Mono<ApiResponse<?>> signup(SignUpModel signUpModel);
}
