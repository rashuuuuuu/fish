package com.website.admin.status.service;

import com.website.common.dto.ApiResponse;
import reactor.core.publisher.Mono;

public interface StatusService {
    Mono<ApiResponse<?>> getAllStatus();
}
