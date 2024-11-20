package com.website.admin.comment.controller;

import com.website.admin.comment.model.ReplyComment;
import com.website.admin.comment.model.ViewCommentRequest;
import com.website.admin.comment.service.CommentService;
import com.website.common.constant.ApiConstant;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.SearchParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.COMMENTS)
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> getComments(@RequestBody @Valid SearchParam searchParam){
        return commentService.getComments(searchParam);
    }
    @PostMapping(ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> viewComment(@RequestBody @Valid ViewCommentRequest viewCommentRequest){
        return commentService.viewComment(viewCommentRequest);
    }
    @PostMapping(ApiConstant.RESPONSE+ApiConstant.SLASH+ApiConstant.CREATE)
    public Mono<ApiResponse<?>> replyComment(@RequestBody @Valid ReplyComment replyComment){
        return commentService.replyComment(replyComment);
    }
    @PostMapping(ApiConstant.RESPONSE+ApiConstant.SLASH+ApiConstant.GET)
    public Mono<ApiResponse<?>> getCommentResponseList(@RequestBody @Valid SearchParam searchParam){
        return commentService.getCommentResponseList(searchParam);
    }
}
