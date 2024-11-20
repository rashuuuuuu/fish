package com.website.admin.adminActivityLog.repo;

import com.website.admin.adminActivityLog.entity.AdminActivityLog;
import com.website.admin.user.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminActivityLogRepository extends JpaRepository<AdminActivityLog, Long> {
    List<AdminActivityLog> findAllByAdminOrderByActivityDateDesc(Admin admin);
}
