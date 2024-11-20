package com.website.repository;

import com.website.entity.CustomerEmailLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerEmailLogRepository extends JpaRepository<CustomerEmailLog, Long> {
    CustomerEmailLog findByOtp(String otp);
}
