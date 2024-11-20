package com.website.admin.customer.service.impl;

import com.website.admin.adminActivityLog.service.AdminActivityService;
import com.website.common.constant.StatusConstant;
import com.website.common.dto.*;
import com.website.common.repo.StatusRepository;
import com.website.common.service.SearchResponse;
import com.website.common.util.ResponseUtil;
import com.website.admin.customer.mapper.CustomerMapper;
import com.website.admin.customer.model.BlockCustomerRequest;
import com.website.admin.customer.model.SearchCustomerResponse;
import com.website.admin.customer.model.UnblockCustomerRequest;
import com.website.admin.customer.model.ViewCustomerRequest;
import com.website.admin.customer.service.CustomerService;
import com.website.entity.Customer;
import com.website.admin.log.mapper.CustomerLogMapper;
import com.website.repository.CustomerRepository;
import com.website.repository.CustomerSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerSearchRepository customerSearchRepository;
    private final SearchResponse searchResponse;
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final StatusRepository statusRepository;    
    private final CustomerLogMapper customerLogMapper;
    private final AdminActivityService adminActivityService;

    @Override
    public Mono<ApiResponse<?>> getCustomerList(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<Customer, SearchCustomerResponse> responseBuilder = SearchResponseWithMapperBuilder.<Customer,SearchCustomerResponse>builder()
                .count(customerSearchRepository::count)
                .searchData(customerSearchRepository::getAll)
                .mapperFunction(this.customerMapper::getCustomerSearchResponse)
                .searchParam(searchParam)
                .build();
        PageableResponse<SearchCustomerResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response,"User List fetched successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> viewCustomerDetail(ViewCustomerRequest viewCustomerRequest) {
        Optional<Customer> optionalCustomer =  customerRepository.findByEmail(viewCustomerRequest.getEmail());
        if (optionalCustomer.isEmpty() || StatusConstant.DELETED.getName().equals(optionalCustomer.get().getStatus().getName())){
            return Mono.just(ResponseUtil.getNotFoundResponse("User not found"));
        }
        CustomerDto customerDto = customerMapper.viewCustomerDetail(optionalCustomer.get());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(customerDto,"User detail fetched successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> blockCustomer(BlockCustomerRequest blockCustomerRequest, Principal connectedUser) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(blockCustomerRequest.getEmail());
        if (optionalCustomer.isEmpty() || StatusConstant.DELETED.getName().equals(optionalCustomer.get().getStatus().getName())){
            return Mono.just(ResponseUtil.getNotFoundResponse("User not found"));
        }
        if (StatusConstant.ACTIVE.getName().equals(optionalCustomer.get().getStatus().getName())){
            Customer customer = optionalCustomer.get();
            customer.setStatus(statusRepository.findByName(StatusConstant.BLOCKED.getName()));
            customer.setActive(false);
            adminActivityService.logActivity(connectedUser, "Block", "User", customer.getUsername());
            customerLogMapper.mapToBlockEntity(customer, blockCustomerRequest,connectedUser);
            customerRepository.save(customer);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("User blocked successfully"));
        }
        else {
            return Mono.just(ResponseUtil.getFailureResponse("User block unsuccessful"));
        }
    }

    @Override
    public Mono<ApiResponse<?>> unblockCustomer(UnblockCustomerRequest unblockCustomerRequest, Principal connectedUser) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(unblockCustomerRequest.getEmail());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (StatusConstant.BLOCKED.getName().equals(customer.getStatus().getName())) {
                customer.setStatus(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
                customer.setActive(true);
                customerRepository.save(customer);
                customerLogMapper.mapToUnblockEntity(customer, unblockCustomerRequest, connectedUser);
                adminActivityService.logActivity(connectedUser, "Unblock", "User", customer.getUsername());
                return Mono.just(ResponseUtil.getSuccessfulApiResponse("User unblocked successfully"));
            }
        }
        return Mono.just(ResponseUtil.getFailureResponse("User unblock failed"));
    }
}
