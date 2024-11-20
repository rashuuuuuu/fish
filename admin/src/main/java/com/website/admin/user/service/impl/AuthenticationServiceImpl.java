package com.website.admin.user.service.impl;

import com.website.admin.core.service.JwtService;
import com.website.admin.user.entity.Admin;
import com.website.admin.user.model.AuthenticationRequest;
import com.website.admin.user.model.AuthenticationResponse;
import com.website.admin.user.repo.AdminRepository;
import com.website.admin.user.service.AuthenticationService;
import com.website.common.constant.StatusConstant;
import com.website.common.dto.ApiResponse;
import com.website.common.repo.StatusRepository;
import com.website.common.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final StatusRepository statusRepository;

    @Override
    public ApiResponse authenticate(AuthenticationRequest request) {
        Optional<Admin> user = adminRepository.findByUsername(request.getUsername());

        if (user.isPresent() && !user.get().getStatus().equals(statusRepository.findByName(StatusConstant.DELETED.getName()))) {
            if (user.get().getStatus().equals(statusRepository.findByName(StatusConstant.ACTIVE.getName()))) {
                try {
                    Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
                    String jwtToken = jwtService.generateToken(user.get());

                    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
                    authenticationResponse.setToken(jwtToken);
                    return ResponseUtil.getSuccessfulApiResponse(authenticationResponse, "Successfully Logged in.");
                } catch (AuthenticationException e) {
                    return ResponseUtil.getFailureResponse("Wrong password. Try again");
                }
            } else if (user.get().getStatus().equals(statusRepository.findByName(StatusConstant.PENDING.getName()))) {
                return ResponseUtil.getFailureResponse("This account is pending verification!");
            }
            return ResponseUtil.getFailureResponse("User is blocked");
        } else {
            return ResponseUtil.getFailureResponse("User not found.");
        }
    }
}