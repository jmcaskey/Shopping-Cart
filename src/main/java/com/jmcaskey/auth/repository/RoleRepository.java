package com.jmcaskey.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmcaskey.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
