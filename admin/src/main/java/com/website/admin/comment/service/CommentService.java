package com.website.admin.comment.service;


import com.website.admin.comment.model.ReplyComment;
import com.website.admin.comment.model.ViewCommentRequest;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.SearchParam;
import reactor.core.publisher.Mono;

public interface CommentService {
    Mono<ApiResponse<?>> getComments(SearchParam searchParam);

    Mono<ApiResponse<?>> viewComment(ViewCommentRequest viewCommentRequest);

    Mono<ApiResponse<?>> replyComment(ReplyComment replyComment);

    Mono<ApiResponse<?>> getCommentResponseList(SearchParam searchParam);
}
