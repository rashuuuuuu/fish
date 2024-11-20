package com.website.admin.role.repository;

import com.website.admin.role.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);

    @Query("Select r from Roles r where r.name not in ('ROOT')")
    List<Roles> getAllRoles();
}
