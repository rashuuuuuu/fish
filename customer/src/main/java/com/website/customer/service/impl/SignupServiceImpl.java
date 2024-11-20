package com.website.customer.service.impl;

import com.website.common.constant.StatusConstant;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.SignUpModel;
import com.website.common.repo.StatusRepository;
import com.website.common.util.ResponseUtil;
import com.website.customer.service.SignupService;
import com.website.entity.Customer;
import com.website.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final StatusRepository statusRepository;

    @Override
    @Transactional
    public Mono<ApiResponse<?>> signup(SignUpModel signUpModel) {

        Optional<Customer> existingCustomerWithEmail = customerRepository.findByEmail(signUpModel.getEmail());
        Optional<Customer> existingCustomerWithPhone = customerRepository.findByMobileNumber(signUpModel.getMobileNumber());

        if (existingCustomerWithEmail.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("The user with email already exist."));
        }
        if (existingCustomerWithPhone.isPresent()) {
            return Mono.just(ResponseUtil.getFailureResponse("The user with phone number already exist."));
        }
            Customer customer = signUpMapToEntity(signUpModel);
            customerRepository.save(customer);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Signup successful"));
        }
    public Customer signUpMapToEntity(SignUpModel signUpModel){
        Customer customer = new Customer();
        customer.setFirstName(signUpModel.getFirstName());
        customer.setLastName(signUpModel.getLastName());
        customer.setMobileNumber(signUpModel.getMobileNumber());
        customer.setEmail(signUpModel.getEmail());
        customer.setUsername(signUpModel.getEmail());
        customer.setPassword(passwordEncoder.encode(signUpModel.getPassword()));
        customer.setStatus(statusRepository.findByName(StatusConstant.PENDING.getName()));
        return customer;
    }
}
