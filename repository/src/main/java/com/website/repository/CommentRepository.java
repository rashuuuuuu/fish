package com.website.repository;

import com.website.entity.Comment;
import com.website.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Comment> findByCode(String code);
    List<Comment> findByCustomer(Customer customer);
}
