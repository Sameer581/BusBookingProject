package com.cg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Role;

public interface RoleRepo extends JpaRepository<Role, RolePk> {
 
}