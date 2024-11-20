package com.website.admin.adminActivityLog.mapper;

import com.website.admin.adminActivityLog.entity.AdminActivityLog;
import com.website.admin.adminActivityLog.model.AdminActivityLogModel;
import com.website.admin.adminActivityLog.model.SearchAdminActivityLogResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AdminActivityLogMapper {
    public abstract List<AdminActivityLogModel> maptoModel(List<AdminActivityLog> adminActivityLogs);


    public abstract SearchAdminActivityLogResponse entityToResponse(AdminActivityLog activityLog);
    public List<SearchAdminActivityLogResponse> getAdminActivityLog(List<AdminActivityLog> activityLogs){
        return activityLogs.stream().map(this::entityToResponse).collect(Collectors.toList());
    }
}
