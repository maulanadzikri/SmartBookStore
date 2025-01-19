package com.smartbookstore.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartbookstore.server.model.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {}
