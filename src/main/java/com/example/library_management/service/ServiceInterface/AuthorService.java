package com.example.library_management.service.ServiceInterface;

import com.example.library_management.dto.AuthorDto;

public interface AuthorService {
  AuthorDto createAuthor(AuthorDto dto);

  AuthorDto getAuthorById(Long id);
}
