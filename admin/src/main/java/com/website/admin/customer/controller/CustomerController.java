package com.website.admin.customer.controller;

import com.website.common.constant.ApiConstant;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.SearchParam;
import com.website.admin.customer.model.BlockCustomerRequest;
import com.website.admin.customer.model.UnblockCustomerRequest;
import com.website.admin.customer.model.ViewCustomerRequest;
import com.website.admin.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> getCustomerList(@RequestBody @Valid SearchParam searchParam){
        return customerService.getCustomerList(searchParam);
    }
    @PostMapping(ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> viewCustomerDetail(@RequestBody @Valid ViewCustomerRequest viewCustomerRequest){
        return customerService.viewCustomerDetail(viewCustomerRequest);
    }
    @PostMapping(ApiConstant.BLOCK)
    public Mono<ApiResponse<?>> blockCustomer(@RequestBody @Valid BlockCustomerRequest blockCustomerRequest, Principal connectedUser){
        return customerService.blockCustomer(blockCustomerRequest,connectedUser);
    }
    @PostMapping(ApiConstant.UNBLOCK)
    public Mono<ApiResponse<?>> unblockCustomer(@RequestBody @Valid UnblockCustomerRequest unblockCustomerRequest, Principal connectedUser){
        return customerService.unblockCustomer(unblockCustomerRequest, connectedUser);
    }
}
