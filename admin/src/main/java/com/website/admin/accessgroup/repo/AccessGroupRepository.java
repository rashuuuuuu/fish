package com.website.admin.accessgroup.repo;

import com.website.admin.accessgroup.entity.AccessGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessGroupRepository extends JpaRepository<AccessGroup,Long> {
    Optional<AccessGroup> findByName(String name);

}
