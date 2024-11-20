package com.website.repository.impl;
import com.website.common.dto.SearchParam;
import com.website.common.util.SearchParamUtil;
import com.website.entity.product.Product;
import com.website.repository.ProductSearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.website.common.constant.SearchParamConstant.*;

@Repository
@RequiredArgsConstructor
public class ProductSearchRepositoryImpl implements ProductSearchRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Long count(SearchParam searchParam) {
        return (Long) em.createQuery("select COUNT(f.id) " +
                        "from Product  f " +
                        "join f.statusProduct s " +
                        " where " +
                        "(:name is null or f.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ")
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .getSingleResult();
    }

    @Override
    public List<Product> getAll(SearchParam searchParam) {
        return em.createQuery("select f " +
                        "from Product  f " +
                        "join f.statusProduct s " +
                        " where " +
                        "(:name is null or f.name like CONCAT('%', :name, '%')) and " +
                        "(:status is null or s.description=:status) ",Product.class)
                .setParameter("name", SearchParamUtil.getString(searchParam, NAME))
                .setParameter("status", SearchParamUtil.getString(searchParam, STATUS))
                .setFirstResult(searchParam.getFirstRow())
                .setMaxResults(searchParam.getPageSize())
                .getResultList();
    }
}
