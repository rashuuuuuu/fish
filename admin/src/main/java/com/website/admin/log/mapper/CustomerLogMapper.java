package com.website.admin.log.mapper;

import com.website.admin.customer.model.BlockCustomerRequest;
import com.website.admin.customer.model.UnblockCustomerRequest;
import com.website.entity.Customer;
import com.website.admin.log.entity.CustomerBlockLog;
import com.website.admin.log.entity.CustomerUnblockLog;
import com.website.admin.log.repo.CustomerBlockLogRepository;
import com.website.admin.log.repo.CustomerUnblockLogRepository;
import com.website.admin.user.repo.AdminRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CustomerLogMapper {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerBlockLogRepository customerBlockLogRepository;
    @Autowired
    private CustomerUnblockLogRepository customerUnblockLogRepository;

    public CustomerBlockLog mapToBlockEntity(Customer customer, BlockCustomerRequest blockCustomerRequest, Principal connectedUser){
        CustomerBlockLog customerBlockLog = new CustomerBlockLog();
        customerBlockLog.setCustomer(customer);
        customerBlockLog.setRemarks(blockCustomerRequest.getRemarks());
        customerBlockLog.setBlockedDate(new Date());
        customerBlockLog.setAdmin(adminRepository.findByUsername(connectedUser.getName()).get());
        return customerBlockLogRepository.save(customerBlockLog);
    }

    public CustomerUnblockLog mapToUnblockEntity(Customer customer, UnblockCustomerRequest unblockCustomerRequest, Principal connectedUser){
        CustomerUnblockLog customerUnblockLog = new CustomerUnblockLog();
        customerUnblockLog.setCustomer(customer);
        customerUnblockLog.setRemarks(unblockCustomerRequest.getRemarks());
        customerUnblockLog.setUnblockedDate(new Date());
        customerUnblockLog.setAdmin(adminRepository.findByUsername(connectedUser.getName()).get());
        return customerUnblockLogRepository.save(customerUnblockLog);
    }
}
