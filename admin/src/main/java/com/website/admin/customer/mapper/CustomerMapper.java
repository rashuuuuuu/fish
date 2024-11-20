package com.website.admin.customer.mapper;

import com.website.common.dto.CustomerDto;
import com.website.admin.customer.model.SearchCustomerResponse;
import com.website.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CustomerMapper {

    public abstract SearchCustomerResponse mapToResponse(Customer customer);
    public List<SearchCustomerResponse> getCustomerSearchResponse(List<Customer> customers){
        return customers.stream().map(this::mapToResponse).collect(Collectors.toList());
    }
    public abstract CustomerDto viewCustomerDetail(Customer customer);
}
