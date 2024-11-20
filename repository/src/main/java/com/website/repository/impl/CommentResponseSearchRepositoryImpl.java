package com.website.repository.impl;

import com.website.common.dto.SearchParam;
import com.website.common.util.SearchParamUtil;
import com.website.entity.CommentResponse;
import com.website.repository.CommentResponseSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.website.common.constant.SearchParamConstant.CUSTOMER;
import static com.website.common.constant.SearchParamConstant.MESSAGE;

@Repository
@RequiredArgsConstructor
public class CommentResponseSearchRepositoryImpl implements CommentResponseSearchRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(cr.id) " +
                        "from CommentResponse  cr " +
                        "join cr.comment.customer " +
                        " where " +
                        "(:message is null or cr.message like CONCAT('%', :message, '%')) and " +
                        "(:customer is null or cr.comment.customer.firstName=:customer) ")
                .setParameter("message", SearchParamUtil.getString(searchParam, MESSAGE))
                .setParameter("customer", SearchParamUtil.getString(searchParam, CUSTOMER))
                .getSingleResult();
    }

    @Override
    public List<CommentResponse> getAll(SearchParam searchParam) {
        return em.createQuery("select cr " +
                        "from CommentResponse  cr " +
                        "join cr.comment.customer " +
                        " where " +
                        "(:message is null or cr.message like CONCAT('%', :message, '%')) and " +
                        "(:customer is null or cr.comment.customer.firstName=:customer) ", CommentResponse.class)
                .setParameter("message", SearchParamUtil.getString(searchParam, MESSAGE))
                .setParameter("customer", SearchParamUtil.getString(searchParam, CUSTOMER))
                .getResultList();
    }
}
