package com.example.library_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library_management.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
