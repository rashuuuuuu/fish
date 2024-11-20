package com.website.customer.profile.mapper;

import com.website.customer.profile.model.CustomerProfileDetailModel;
import com.website.customer.profile.model.EditProfileRequest;
import com.website.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CustomerProfileMapper {
    public abstract CustomerProfileDetailModel getUserProfileDetailModel(Customer customer);
    public Customer editCustomerProfile(EditProfileRequest editProfileRequest, Customer customer){
        customer.setFirstName(editProfileRequest.getFirstName());
        customer.setLastName(editProfileRequest.getLastName());
        customer.setMobileNumber(editProfileRequest.getMobileNumber());
        customer.setUsername(editProfileRequest.getUsername());
        return customer;
    }
}
