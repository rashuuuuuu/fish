package com.website.admin.log.mapper;

import com.website.admin.log.entity.AdminBlockLog;
import com.website.admin.log.entity.AdminUnblockLog;
import com.website.admin.log.repo.AdminBlockLogRepository;
import com.website.admin.log.repo.AdminUnblockLogRepository;
import com.website.admin.user.entity.Admin;
import com.website.admin.user.model.request.BlockAdminRequest;
import com.website.admin.user.model.request.UnblockAdminUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AdminLogMapper {

    @Autowired
    private AdminBlockLogRepository adminBlockLogRepository;

    @Autowired
    private AdminUnblockLogRepository adminUnblockLogRepository;

    public AdminBlockLog mapToBlockEntity(Admin admin, BlockAdminRequest blockAdminRequest, Principal connectedUser){
        AdminBlockLog adminBlockLog = new AdminBlockLog();
        adminBlockLog.setAdmin(admin);
        adminBlockLog.setRemarks(blockAdminRequest.getRemarks());
        adminBlockLog.setBlockedDate(new Date());
        adminBlockLog.setBlockedBy(connectedUser.getName());
        return adminBlockLogRepository.save(adminBlockLog);
    }
    public AdminUnblockLog mapToUnblockEntity(Admin admin, UnblockAdminUserRequest unblockAdminUserRequest, Principal connectedUser){
        AdminUnblockLog adminUnblockLog = new AdminUnblockLog();
        adminUnblockLog.setAdmin(admin);
        adminUnblockLog.setRemarks(unblockAdminUserRequest.getRemarks());
        adminUnblockLog.setUnblockedDate(new Date());
        adminUnblockLog.setUnblockedBy(connectedUser.getName());
        return adminUnblockLogRepository.save(adminUnblockLog);
    }

}
