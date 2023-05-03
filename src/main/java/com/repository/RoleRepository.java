package com.repository;

import com.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface RoleRepository extends JpaRepository<Role, Id> {
    Role findByName(String name);
}
