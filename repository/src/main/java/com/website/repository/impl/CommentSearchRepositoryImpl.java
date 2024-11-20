package com.website.repository.impl;

import com.website.common.dto.SearchParam;
import com.website.common.util.SearchParamUtil;
import com.website.entity.Comment;
import com.website.repository.CommentSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.website.common.constant.SearchParamConstant.CUSTOMER;
import static com.website.common.constant.SearchParamConstant.RECORDED_DATE;
import static com.website.common.constant.SearchParamConstant.SUBJECT;


@Repository
@RequiredArgsConstructor
public class CommentSearchRepositoryImpl implements CommentSearchRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(q.id) " +
                        "from Comment  q " +
                        "join q.customer c " +
                        " where " +
                        "(:recordedDate is null or FUNCTION('DATE_FORMAT', q.recordedDate, '%Y-%m-%d') = :recordedDate) and " +
                        "(:subject is null or q.subject like CONCAT('%', :subject, '%')) and " +
                        "(:customer is null or c.fullName=:customer) ")
                .setParameter("recordedDate", SearchParamUtil.getString(searchParam,RECORDED_DATE))
                .setParameter("subject", SearchParamUtil.getString(searchParam, SUBJECT))
                .setParameter("customer", SearchParamUtil.getString(searchParam, CUSTOMER))
                .getSingleResult();
    }
    @Override
    public List<Comment> getAll(SearchParam searchParam) {
        return em.createQuery("select c " +
                        "from Comment  c " +
                        "join c.customer cu " +
                        " where " +
                        "(:recordedDate is null or FUNCTION('DATE_FORMAT', c.recordedDate, '%Y-%m-%d') = :recordedDate) and " +
                        "(:subject is null or c.subject like CONCAT('%', :subject, '%')) and " +
                        "(:customer is null or cu.username=:customer) ", Comment.class)
                .setParameter("recordedDate",SearchParamUtil.getString(searchParam,RECORDED_DATE))
                .setParameter("subject", SearchParamUtil.getString(searchParam, SUBJECT))
                .setParameter("customer", SearchParamUtil.getString(searchParam, CUSTOMER))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}
