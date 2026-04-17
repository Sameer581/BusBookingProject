package com.cg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Role;
import com.cg.entity.RolePk;

public interface RoleRepo extends JpaRepository<Role, RolePk> {
 
}