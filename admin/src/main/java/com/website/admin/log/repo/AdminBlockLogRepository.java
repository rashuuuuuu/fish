package com.website.admin.log.repo;

import com.website.admin.log.entity.AdminBlockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminBlockLogRepository extends JpaRepository<AdminBlockLog, Long> {
}
