package com.website.admin.log.repo;

import com.website.admin.log.entity.AccessGroupUnblockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessGroupUnblockLogRepository extends JpaRepository<AccessGroupUnblockLog, Long> {
}