package com.website.admin.log.repo;

import com.website.admin.log.entity.AdminUnblockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUnblockLogRepository extends JpaRepository<AdminUnblockLog, Long> {
}
