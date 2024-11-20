package com.website.customer.controller;

import com.website.common.constant.ApiConstant;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.CommentModel;
import com.website.customer.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(path = ApiConstant.COMMENTS)
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse<?>> createComment(@RequestBody @Valid CommentModel commentModel, Principal connectedUser){
        return commentService.createComment(commentModel,connectedUser);
    }
    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> getComments(Principal connectedUser){
        return commentService.getComments(connectedUser);
    }

}
