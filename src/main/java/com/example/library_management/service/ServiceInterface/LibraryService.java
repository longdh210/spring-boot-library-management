package com.example.library_management.service.ServiceInterface;

import com.example.library_management.dto.LibraryDto;

public interface LibraryService {
  LibraryDto createLibrary(String name);

  LibraryDto getLibraryById(Long id);
}
