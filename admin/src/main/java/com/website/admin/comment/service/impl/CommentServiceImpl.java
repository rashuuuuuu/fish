package com.website.admin.comment.service.impl;

import com.website.admin.comment.mapper.CommentMapper;
import com.website.admin.comment.model.CommentResponseDto;
import com.website.admin.comment.model.CommentSearchResponse;
import com.website.admin.comment.model.ReplyComment;
import com.website.admin.comment.model.ViewCommentRequest;
import com.website.admin.comment.service.CommentService;
import com.website.common.dto.*;
import com.website.common.service.SearchResponse;
import com.website.common.util.ResponseUtil;
import com.website.entity.Comment;
import com.website.entity.CommentResponse;
import com.website.repository.CommentRepository;
import com.website.repository.CommentResponseRepository;
import com.website.repository.CommentResponseSearchRepository;
import com.website.repository.CommentSearchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentSearchRepository commentSearchRepository;
    private final CommentMapper commentMapper;
    private final SearchResponse searchResponse;
    private final CommentRepository commentRepository;
    private final CommentResponseRepository commentResponseRepository;
    private final CommentResponseSearchRepository commentResponseSearchRepository;

    @Override
    public Mono<ApiResponse<?>> getComments(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<Comment, CommentSearchResponse> responseBuilder = SearchResponseWithMapperBuilder.<Comment, CommentSearchResponse>builder()
                .count(commentSearchRepository::count)
                .searchData(commentSearchRepository::getAll)
                .mapperFunction(this.commentMapper::getCommentSearchResponse)
                .searchParam(searchParam)
                .build();
        PageableResponse<CommentSearchResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response,"All Comments fetched successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> viewComment(ViewCommentRequest viewCommentRequest) {
        Optional<Comment> optionalComment = commentRepository.findByCode(viewCommentRequest.getCode());
        if (optionalComment.isEmpty()){
            return Mono.just(ResponseUtil.getNotFoundResponse("Comment not found"));
        }
        CommentDto commentDto = commentMapper.viewComment(optionalComment.get());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(commentDto,"Comment detail fetched successfully"));
    }

    @Override
    @Transactional
    public Mono<ApiResponse<?>> replyComment(ReplyComment replyComment) {
        CommentResponse commentResponse = commentMapper.sendCommentResponse(replyComment);
        if (commentResponse == null){
            return Mono.just(ResponseUtil.getFailureResponse("Comment already responded"));
        }
        commentResponseRepository.save(commentResponse);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Comment responded successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> getCommentResponseList(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<CommentResponse, CommentResponseDto> responseBuilder = SearchResponseWithMapperBuilder.<CommentResponse,CommentResponseDto>builder()
                .count(commentResponseSearchRepository::count)
                .searchData(commentResponseSearchRepository::getAll)
                .mapperFunction(this.commentMapper::getCommentResponseSearchRes)
                .searchParam(searchParam)
                .build();
        PageableResponse<CommentResponseDto> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response,"Comment response list fetched successfully"));
    }

}
