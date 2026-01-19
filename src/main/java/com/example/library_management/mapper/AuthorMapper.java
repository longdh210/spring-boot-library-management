package com.example.library_management.mapper;

import com.example.library_management.dto.AuthorDto;
import com.example.library_management.entity.Author;

public class AuthorMapper {
  public static AuthorDto toDto(Author author) {
    return new AuthorDto(author.getId(), author.getName(), author.getDayOfBirth());
  }
}
