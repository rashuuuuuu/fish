package com.website.customer.service;

import com.website.common.dto.ApiResponse;
import com.website.common.dto.CommentModel;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface CommentService {
    Mono<ApiResponse<?>> createComment(CommentModel commentModel, Principal connectedUser);
    Mono<ApiResponse<?>> getComments(Principal connectedUser);
}
