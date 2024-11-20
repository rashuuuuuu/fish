package com.website.admin.adminActivityLog.service;

import com.website.admin.adminActivityLog.entity.AdminActivityLog;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.SearchParam;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface AdminActivityService {
    AdminActivityLog logActivity(Principal connectedUser, String action, String object, String objectIdentifier);
    Mono<ApiResponse<?>> getAdminActivityLogs(Principal connectedUser);
    Mono<ApiResponse<?>> getAllActivityLog(SearchParam searchParam, Principal connectedUser);
    Mono<ApiResponse<?>> getAdminActivityLog(SearchParam searchParam, Principal connectedUser);
}
