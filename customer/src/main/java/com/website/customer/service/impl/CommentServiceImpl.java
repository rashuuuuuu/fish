package com.website.customer.service.impl;

import com.website.common.dto.ApiResponse;
import com.website.common.dto.CommentModel;
import com.website.common.dto.request.CommentResponseDto;
import com.website.common.util.ResponseUtil;
import com.website.customer.mapper.CommentsMapper;
import com.website.customer.service.CommentService;
import com.website.entity.Customer;
import com.website.entity.Comment;
import com.website.entity.CommentResponse;
import com.website.repository.CommentRepository;
import com.website.repository.CommentResponseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class  CommentServiceImpl implements CommentService {
    private final CommentsMapper commentsMapper;
    private final CommentRepository commentRepository;
    private final CommentResponseRepository commentResponseRepository;

    @Override
    public Mono<ApiResponse<?>> createComment(CommentModel commentModel, Principal connectedUser){
        var customer = ((Customer)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        Comment comment = commentsMapper.saveComments(commentModel);
        comment.setCustomer(customer);
        commentRepository.save(comment);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Comment created successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> getComments(Principal connectedUser) {
        var customer = ((Customer) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        List<Comment> comment = commentRepository.findByCustomer(customer);
        List<CommentResponse> commentResponse = commentResponseRepository.findByCommentIn(comment);
        List<CommentResponseDto> response = commentResponse.stream()
                .map(res -> {
                    CommentResponseDto dto = new CommentResponseDto();
                    dto.setCode(res.getCode());
                    dto.setResponseDate(res.getResponseDate());
                    dto.setMessage(res.getMessage());
                    return dto;
                })
                .collect(Collectors.toList());
        return Mono.just(ResponseUtil.getSuccessfulApiResponseWithData(response, "Comment abstract successfully"));
    }
}
