package com.website.customer.service.impl;

import com.website.common.dto.ApiResponse;
import com.website.common.util.ResponseUtil;
import com.website.customer.core.service.JwtService;
import com.website.customer.models.AuthenticationRequest;
import com.website.customer.models.AuthenticationResponse;
import com.website.customer.service.AuthenticationService;
import com.website.entity.Customer;
import com.website.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CustomerRepository customerRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public ApiResponse authenticate(AuthenticationRequest request) {
        Optional<Customer> user = customerRepository.findByUsername(request.getUsername());

        if (user.isPresent()) {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            String jwtToken = jwtService.generateToken((UserDetails) user.get());

            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setToken(jwtToken);
            return ResponseUtil.getSuccessfulApiResponse(authenticationResponse, "Successfully Logged in.");
        } else {
            return ResponseUtil.getFailureResponse("User not found.");
        }
    }
}