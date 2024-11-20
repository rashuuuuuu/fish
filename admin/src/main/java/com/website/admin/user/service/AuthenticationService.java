package com.website.admin.user.service;

import com.website.admin.user.model.AuthenticationRequest;
import com.website.admin.user.model.AuthenticationResponse;
import com.website.common.dto.ApiResponse;

public interface AuthenticationService {

    ApiResponse<AuthenticationResponse> authenticate(AuthenticationRequest request);
}
