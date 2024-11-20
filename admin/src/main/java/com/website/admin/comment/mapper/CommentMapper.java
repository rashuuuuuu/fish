package com.website.admin.comment.mapper;
import com.website.admin.comment.model.CommentResponseDto;
import com.website.admin.comment.model.CommentSearchResponse;
import com.website.admin.comment.model.ReplyComment;
import com.website.common.dto.CommentDto;
import com.website.common.exception.NotFoundException;
import com.website.entity.Comment;
import com.website.entity.CommentResponse;
import com.website.repository.CommentRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CommentMapper {


    @Autowired
    protected CommentRepository commentRepository;

    public abstract CommentSearchResponse mapToResponse(Comment comment);

    public List<CommentSearchResponse> getCommentSearchResponse(List<Comment> comments){
        return comments.stream().map(this::mapToResponse).collect(Collectors.toList());
    }
    public abstract CommentDto viewComment(Comment comment);

    public CommentResponse sendCommentResponse(ReplyComment replyComment){
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setMessage(replyComment.getMessage());
        Comment comment = commentRepository.findByCode(replyComment.getCommentModel().getCode()).orElseThrow(()->
                new NotFoundException("Comment not found"));
        if (comment.isReplied()){
            return null;
        }
        commentResponse.setComment(comment);
        commentResponse.setCode(UUID.randomUUID().toString());
        commentResponse.setResponseDate((java.sql.Date) new Date());

        comment.setReplied(true);
        commentRepository.save(comment);

        return  commentResponse;
    }

    public abstract CommentResponseDto mapToCommentResponse(CommentResponse commentResponse);
    public List<CommentResponseDto> getCommentResponseSearchRes(List<CommentResponse> commentResponseList){
        return commentResponseList.stream().map(this::mapToCommentResponse).collect(Collectors.toList());
    }

}
