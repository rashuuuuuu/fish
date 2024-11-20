package com.website.repository.impl;


import com.website.common.util.SearchParamUtil;
import com.website.entity.Customer;
import com.website.common.dto.SearchParam;
import com.website.repository.CustomerSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.website.common.constant.SearchParamConstant.*;

@Repository
@RequiredArgsConstructor
public class CustomerSearchRepositoryImpl implements CustomerSearchRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(c.id) " +
                        "from Customer  c " +
                        "join c.status s " +
                        " where " +
                        "(:fullName is null or c.username like CONCAT('%', :fullName, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("fullName", SearchParamUtil.getString(searchParam, USERNAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getSingleResult();
    }

    @Override
    public List<Customer> getAll(SearchParam searchParam) {
        return em.createQuery("select c " +
                        "from Customer  c " +
                        "join c.status s " +
                        " where " +
                        "(:fullName is null or c.username like CONCAT('%', :fullName, '%')) and " +
                        "(:status is null or s.description=:status) "+
                        "order by c.registeredDate desc", Customer.class)
                .setParameter("fullName", SearchParamUtil.getString(searchParam, USERNAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}
