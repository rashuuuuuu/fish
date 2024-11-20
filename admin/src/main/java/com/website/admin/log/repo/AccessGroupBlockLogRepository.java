package com.website.admin.log.repo;

import com.website.admin.log.entity.AccessGroupBlockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessGroupBlockLogRepository extends JpaRepository<AccessGroupBlockLog, Long> {
}
