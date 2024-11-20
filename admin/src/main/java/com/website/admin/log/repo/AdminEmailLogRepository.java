package com.website.admin.log.repo;

import com.website.admin.log.entity.AdminEmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminEmailLogRepository extends JpaRepository<AdminEmailLog,Long> {
    Optional<AdminEmailLog> findByUuid(String uuid);
}
