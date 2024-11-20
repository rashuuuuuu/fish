package com.website.repository;

import com.website.entity.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate,Long> {
    EmailTemplate findEmailTemplateByName(String name);
    Optional<EmailTemplate> findByName(String name);
}
