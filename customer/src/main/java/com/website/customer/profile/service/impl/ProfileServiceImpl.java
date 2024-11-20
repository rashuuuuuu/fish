package com.website.customer.profile.service.impl;


import com.website.common.dto.ApiResponse;
import com.website.common.util.ResponseUtil;
import com.website.customer.profile.mapper.CustomerProfileMapper;
import com.website.customer.profile.model.ChangePasswordRequest;
import com.website.customer.profile.model.CustomerProfileDetailModel;
import com.website.customer.profile.model.EditProfileRequest;
import com.website.customer.profile.service.ProfileService;
import com.website.entity.Customer;
import com.website.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final CustomerRepository customerRepository;
    private final CustomerProfileMapper customerProfileMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<ApiResponse<?>> getProfileDetail(Principal connectedUser) {
        var customer= ((Customer) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        CustomerProfileDetailModel customerProfileDetailModel = customerProfileMapper.getUserProfileDetailModel(customer);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(customerProfileDetailModel,"profile fetched successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> changePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser) {
        var customer = ((Customer) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());

        if(!passwordEncoder.matches(changePasswordRequest.getOldPassword(),customer.getPassword())){
            return Mono.just(ResponseUtil.getFailureResponse("Old password is incorrect"));
        }

        if(!changePasswordRequest.getPassword().equals(changePasswordRequest.getConfirmPassword())){
            return Mono.just(ResponseUtil.getFailureResponse("The passwords do not match"));
        }
        else {
            customer.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
            customer.setPasswordChangeDate(new Date());
            customerRepository.save(customer);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Password changed successfully"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> editProfile(EditProfileRequest editProfileRequest, Principal connectedUser) {
        var customer = ((Customer) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        Customer updatingCustomer = customerProfileMapper.editCustomerProfile(editProfileRequest, customer);
        customerRepository.save(updatingCustomer);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Profile updated successfully"));
    }
}
