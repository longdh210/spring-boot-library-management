package com.example.library_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library_management.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
