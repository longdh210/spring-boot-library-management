package com.example.library_management.mapper;

import com.example.library_management.dto.LibraryDto;
import com.example.library_management.entity.Library;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LibraryMapper {
  LibraryMapper INSTANCE = Mappers.getMapper(LibraryMapper.class);

  @Mapping(source = "library.books", target = "books")
  LibraryDto toDto(Library library);
}
