package com.example.library_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library_management.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {

}
