package com.website.admin.log.repo;

import com.website.admin.log.entity.CustomerBlockLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerBlockLogRepository extends JpaRepository<CustomerBlockLog, Long> {
}
