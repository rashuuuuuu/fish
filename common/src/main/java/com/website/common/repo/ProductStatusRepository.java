package com.website.common.repo;

import com.website.entity.StatusProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatusRepository extends JpaRepository<StatusProduct,Long> {
    StatusProduct findByName(String name);
//    StatusProduct findByCode(String name);
}
