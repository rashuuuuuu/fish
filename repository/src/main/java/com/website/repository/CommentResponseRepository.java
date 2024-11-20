package com.website.repository;

import com.website.entity.Comment;
import com.website.entity.CommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentResponseRepository extends JpaRepository<CommentResponse, Long> {
    Optional<CommentResponse> findByCode(String code);

    List<CommentResponse> findByCommentIn(List<Comment> comment);
}
