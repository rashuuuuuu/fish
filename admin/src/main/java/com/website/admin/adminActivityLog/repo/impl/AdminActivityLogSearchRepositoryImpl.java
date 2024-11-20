package com.website.admin.adminActivityLog.repo.impl;

import com.website.admin.adminActivityLog.entity.AdminActivityLog;
import com.website.admin.adminActivityLog.repo.AdminActivityLogSearchRepository;
import com.website.common.dto.SearchParam;
import com.website.common.util.SearchParamUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.website.common.constant.ApiConstant.END_DATE;
import static com.website.common.constant.ApiConstant.START_DATE;
import static com.website.common.constant.SearchParamConstant.*;

@Repository
@RequiredArgsConstructor
public class AdminActivityLogSearchRepositoryImpl implements AdminActivityLogSearchRepository {
    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(al.id)" +
                        "from AdminActivityLog  al " +
                        "join al.admin aa" +
                        " where " +
                        "(:admin is null or aa.username like CONCAT('%', :admin, '%') )"
                        + " and (:startDate is null or al.activityDate >= :startDate)"
                        + " and (:endDate is null or al.activityDate <= :endDate)" )
                .setParameter("admin", SearchParamUtil.getString(searchParam, ADMIN))
                .setParameter("startDate", SearchParamUtil.getDateWithTime(searchParam, START_DATE,"yyyy-MM-dd"))
                .setParameter("endDate", SearchParamUtil.getDateWithTime(searchParam, END_DATE,"yyyy-MM-dd"))
                .getSingleResult();
    }

    @Override
    public List<AdminActivityLog> getAll(SearchParam searchParam) {
        return em.createQuery("select al " +
                        "from AdminActivityLog  al " +
                        "join al.admin aa" +
                        " where " +
                        "(:admin is null or aa.username like CONCAT('%', :admin, '%') )"
                        + " and (:startDate is null or al.activityDate >= :startDate)"
                        + " and (:endDate is null or al.activityDate <= :endDate)" +
                        "order by al.activityDate desc ", AdminActivityLog.class)
                .setParameter("admin", SearchParamUtil.getString(searchParam, ADMIN))
                .setParameter("startDate", SearchParamUtil.getDateWithTime(searchParam, START_DATE,"yyyy-MM-dd"))
                .setParameter("endDate", SearchParamUtil.getDateWithTime(searchParam, END_DATE,"yyyy-MM-dd"))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}
