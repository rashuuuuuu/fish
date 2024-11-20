package com.website.admin.log.mapper;

import com.website.admin.accessgroup.entity.AccessGroup;
import com.website.admin.accessgroup.model.BlockAccessGroupRequest;
import com.website.admin.accessgroup.model.UnblockAccessGroupRequest;
import com.website.admin.log.entity.AccessGroupBlockLog;
import com.website.admin.log.entity.AccessGroupUnblockLog;
import com.website.admin.log.repo.AccessGroupBlockLogRepository;
import com.website.admin.log.repo.AccessGroupUnblockLogRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Date;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AccessGroupLogMapper {

    @Autowired
    private AccessGroupBlockLogRepository accessGroupBlockLogRepository;

    @Autowired
    private AccessGroupUnblockLogRepository accessGroupUnblockLogRepository;

    public AccessGroupBlockLog mapToBlock(AccessGroup accessGroup, BlockAccessGroupRequest blockAccessGroupRequest, Principal connectedUser){
        AccessGroupBlockLog accessGroupBlockLog = new AccessGroupBlockLog();
        accessGroupBlockLog.setAccessGroup(accessGroup);
        accessGroupBlockLog.setRemarks(blockAccessGroupRequest.getRemarks());
        accessGroupBlockLog.setBlockedBy(connectedUser.getName());
        accessGroupBlockLog.setBlockedDate(new Date());
        return accessGroupBlockLogRepository.save(accessGroupBlockLog);
    }
    public AccessGroupUnblockLog mapToUnblock(AccessGroup accessGroup, UnblockAccessGroupRequest unblockAccessGroupRequest, Principal connectedUser){
        AccessGroupUnblockLog accessGroupUnblockLog= new AccessGroupUnblockLog();
        accessGroupUnblockLog.setAccessGroup(accessGroup);
        accessGroupUnblockLog.setRemarks(unblockAccessGroupRequest.getRemarks());
        accessGroupUnblockLog.setUnblockedBy(connectedUser.getName());
        accessGroupUnblockLog.setUnblockedDate(new Date());
        return accessGroupUnblockLogRepository.save(accessGroupUnblockLog);
    }
}
