package com.website.admin.user.repo.impl;

import com.website.admin.user.entity.Admin;
import com.website.admin.user.repo.AdminUserSearchRepository;
import com.website.common.dto.SearchParam;
import com.website.common.util.SearchParamUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.website.common.constant.SearchParamConstant.*;

@Repository
@RequiredArgsConstructor
public class AdminUserSearchRepositoryImpl implements AdminUserSearchRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(a.id) " +
                        "from Admin  a " +
                        "join Status s on s.id=a.status.id " +
                        "join a.accessGroup ag"+
                        " where " +
                        "(:accessGroup is null or ag.name = :accessGroup) and " +
                        "(:name is null or a.name like CONCAT('%', :name, '%')) and " +
                        "(:email is null or a.email like CONCAT('%', :email, '%')) and " +
                        "(:mobileNumber is null or a.mobileNumber like CONCAT('%', :mobileNumber, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("mobileNumber", SearchParamUtil.getString(searchParam,MOBILE_NUMBER))
                .setParameter("email",SearchParamUtil.getString(searchParam,EMAIL))
                .setParameter("accessGroup",SearchParamUtil.getString(searchParam,ACCESS_GROUP))
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getSingleResult();
    }
    @Override
    public List<Admin> getAll(SearchParam searchParam) {
        return em.createQuery("select a " +
                        "from Admin  a " +
                        "join Status s on s.id=a.status.id " +
                        "join a.accessGroup ag"+
                        " where " +
                        "(:accessGroup is null or ag.name = :accessGroup) and " +
                        "(:name is null or a.name like CONCAT('%', :name, '%')) and " +
                        "(:email is null or a.email like CONCAT('%', :email, '%')) and " +
                        "(:mobileNumber is null or a.mobileNumber like CONCAT('%', :mobileNumber, '%')) and " +
                        "(:status is null or s.description=:status) and "+
                        "a.username <> :username and "+
                        "a.isSuperAdmin = false",Admin.class)
                .setParameter("mobileNumber",SearchParamUtil.getString(searchParam,MOBILE_NUMBER))
                .setParameter("email",SearchParamUtil.getString(searchParam,EMAIL))
                .setParameter("accessGroup",SearchParamUtil.getString(searchParam,ACCESS_GROUP))
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .setParameter("username", SearchParamUtil.getString(searchParam,USERNAME))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}
