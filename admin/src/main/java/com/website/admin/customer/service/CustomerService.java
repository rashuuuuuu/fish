package com.website.admin.customer.service;

import com.website.common.dto.ApiResponse;
import com.website.common.dto.SearchParam;
import com.website.admin.customer.model.BlockCustomerRequest;
import com.website.admin.customer.model.UnblockCustomerRequest;
import com.website.admin.customer.model.ViewCustomerRequest;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface CustomerService {
    Mono<ApiResponse<?>> getCustomerList(SearchParam searchParam);
    Mono<ApiResponse<?>> viewCustomerDetail(ViewCustomerRequest viewCustomerRequest);
    Mono<ApiResponse<?>> blockCustomer(BlockCustomerRequest blockCustomerRequest, Principal connectedUser);
    Mono<ApiResponse<?>> unblockCustomer(UnblockCustomerRequest unblockCustomerRequest, Principal connectedUser);
}
