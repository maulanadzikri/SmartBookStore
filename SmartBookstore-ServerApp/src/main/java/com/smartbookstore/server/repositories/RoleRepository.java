package com.smartbookstore.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartbookstore.server.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {}
