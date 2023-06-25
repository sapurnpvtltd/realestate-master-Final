package com.purnabhu.sales.repository;

import com.purnabhu.sales.entities.ERole;
import com.purnabhu.sales.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, String> {
    Optional<Roles> findByRoleName(ERole roleName);
    Optional<Roles> findByRoleId(ERole roleId);
}
