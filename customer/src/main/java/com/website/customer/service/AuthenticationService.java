package com.website.customer.service;

import com.website.customer.models.AuthenticationRequest;
import com.website.customer.models.AuthenticationResponse;
import com.website.common.dto.ApiResponse;

public interface AuthenticationService {
    ApiResponse<AuthenticationResponse> authenticate(AuthenticationRequest request);
}
