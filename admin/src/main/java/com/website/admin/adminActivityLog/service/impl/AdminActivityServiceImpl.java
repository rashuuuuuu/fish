package com.website.admin.adminActivityLog.service.impl;

import com.website.admin.adminActivityLog.entity.AdminActivityLog;
import com.website.admin.adminActivityLog.mapper.AdminActivityLogMapper;
import com.website.admin.adminActivityLog.model.AdminActivityLogModel;
import com.website.admin.adminActivityLog.model.SearchAdminActivityLogResponse;
import com.website.admin.adminActivityLog.repo.AdminActivityLogRepository;
import com.website.admin.adminActivityLog.repo.AdminActivityLogSearchRepository;
import com.website.admin.adminActivityLog.service.AdminActivityService;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.PageableResponse;
import com.website.common.dto.SearchParam;
import com.website.common.dto.SearchResponseWithMapperBuilder;
import com.website.common.service.SearchResponse;
import com.website.common.util.ResponseUtil;
import com.website.admin.user.entity.Admin;
import com.website.admin.user.repo.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.website.common.constant.SearchParamConstant.ADMIN;

@Service
@RequiredArgsConstructor
public class AdminActivityServiceImpl implements AdminActivityService {
    private final AdminActivityLogRepository adminActivityLogRepository;
    private final AdminRepository adminRepository;
    private final AdminActivityLogMapper adminActivityLogMapper;
    private final AdminActivityLogSearchRepository adminActivityLogSearchRepository;
    private final SearchResponse searchResponse;

    @Override
    public AdminActivityLog logActivity(Principal connectedUser, String action, String object, String objectIdentifier) {
        AdminActivityLog adminActivityLog = new AdminActivityLog();
        adminActivityLog.setAdmin(adminRepository.findByUsername(connectedUser.getName()).get());
        adminActivityLog.setActivityDate(LocalDateTime.now());
        adminActivityLog.setAction(action);
        adminActivityLog.setObject(object);
        adminActivityLog.setObjectIdentifier(objectIdentifier);
        return adminActivityLogRepository.save(adminActivityLog);
    }

    @Override
    public Mono<ApiResponse<?>> getAdminActivityLogs(Principal connectedUser) {
        if (connectedUser == null || connectedUser.getName() == null) {
            return Mono.just(ResponseUtil.getFailureResponse("Please login first"));
        }
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(connectedUser.getName());
        if (optionalAdmin.isPresent()){
            List<AdminActivityLog> adminActivityLogs = adminActivityLogRepository.findAllByAdminOrderByActivityDateDesc(optionalAdmin.get());
            if (adminActivityLogs.isEmpty()){
                return Mono.just(ResponseUtil.getFailureResponse("No logs for admin"));
            }
            List<AdminActivityLogModel> adminActivityLogModels = adminActivityLogMapper.maptoModel(adminActivityLogs);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(adminActivityLogModels,"Activity logs fetched successfully"));
        }
        return Mono.just(ResponseUtil.getFailureResponse("Admin not found"));
    }

    @Override
    public Mono<ApiResponse<?>> getAllActivityLog(SearchParam searchParam, Principal connectedUser) {
        if (connectedUser == null || connectedUser.getName() == null) {
            return Mono.just(ResponseUtil.getFailureResponse("Please login first"));
        }
        Admin admin = adminRepository.findByUsername(connectedUser.getName()).get();
        if (admin.isSuperAdmin() || Objects.equals(admin.getAccessGroup().getName(), "Super Admin")) {
            SearchResponseWithMapperBuilder<AdminActivityLog, SearchAdminActivityLogResponse> responseBuilder = SearchResponseWithMapperBuilder
                    .<AdminActivityLog, SearchAdminActivityLogResponse>builder()
                    .count(adminActivityLogSearchRepository::count)
                    .searchData(adminActivityLogSearchRepository::getAll)
                    .mapperFunction(this.adminActivityLogMapper::getAdminActivityLog)
                    .searchParam(searchParam).build();
            PageableResponse<SearchAdminActivityLogResponse> response = searchResponse.getSearchResponse(responseBuilder);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Activity log fetched successfully"));
        }
        return Mono.just(ResponseUtil.getNotFoundResponse("You are not authorized to view activitylog"));
    }

    @Override
    public Mono<ApiResponse<?>> getAdminActivityLog(SearchParam searchParam, Principal connectedUser) {
        Optional<Admin> admin = adminRepository.findByUsername(connectedUser.getName());
        if (admin.isPresent()){
            searchParam.getParam().put(ADMIN, admin.get().getUsername());
            SearchResponseWithMapperBuilder<AdminActivityLog, SearchAdminActivityLogResponse> responseBuilder = SearchResponseWithMapperBuilder
                    .<AdminActivityLog, SearchAdminActivityLogResponse>builder()
                    .count(adminActivityLogSearchRepository::count)
                    .searchData(adminActivityLogSearchRepository::getAll)
                    .mapperFunction(this.adminActivityLogMapper::getAdminActivityLog)
                    .searchParam(searchParam).build();
            PageableResponse<SearchAdminActivityLogResponse> response = searchResponse.getSearchResponse(responseBuilder);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Activity log fetched successfully"));
        }
        return Mono.just(ResponseUtil.getFailureResponse("Please login first"));
    }
}
