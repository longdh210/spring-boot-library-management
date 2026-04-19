package com.example.library_management.mapper;

import com.example.library_management.dto.AuthorDto;
import com.example.library_management.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
  AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

  AuthorDto toDto(Author author);
}
