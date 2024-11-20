package com.website.customer.mapper;

import com.website.common.dto.CommentModel;
import com.website.common.dto.request.CommentResponseDto;
import com.website.entity.CommentResponse;
import com.website.entity.Comment;
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
public abstract class CommentsMapper {

    @Autowired
    protected CommentRepository commentRepository;
    public Comment saveComments(CommentModel commentModel) {
        Comment comment = new Comment();
        comment.setSubject(commentModel.getSubject());
        comment.setDescription(commentModel.getDescription());
        comment.setRecordedDate(new Date());
        comment.setCode(UUID.randomUUID().toString());
        return comment;
    }
    public abstract CommentResponseDto mapToCommentResponse(CommentResponse commentResponse);
    public List<CommentResponseDto> getCommentResponseSearchRes(List<CommentResponse> commentResponseList){
        return commentResponseList.stream().map(this::mapToCommentResponse).collect(Collectors.toList());
    }
}
