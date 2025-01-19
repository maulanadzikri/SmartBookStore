package com.smartbookstore.server.repositories;

import com.smartbookstore.server.model.entity.UserDetail;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {}