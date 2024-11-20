package com.website.admin.log.repo;

import com.website.admin.log.entity.CustomerUnblockLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerUnblockLogRepository extends JpaRepository<CustomerUnblockLog, Long> {
}
