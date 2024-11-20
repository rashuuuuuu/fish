package com.website.admin.status.service.impl;

import com.website.common.dto.ApiResponse;
import com.website.common.repo.StatusRepository;
import com.website.common.util.ResponseUtil;
import com.website.entity.Status;
import com.website.admin.status.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Override
    public Mono<ApiResponse<?>> getAllStatus() {
        List<Status> statusList = statusRepository.findAll();
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(statusList,"Status list fetched successfully"));
    }
}
