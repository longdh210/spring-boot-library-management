package com.example.library_management.mapper;

import com.example.library_management.dto.LibraryDto;
import com.example.library_management.entity.Library;

public class LibraryMapper {
  public static LibraryDto toDto(Library library) {
    return new LibraryDto(library.getId(), library.getName(),
        library.getBooks().stream().map(BookMapper::toDto).toList());
  }
}
