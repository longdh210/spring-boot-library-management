package com.example.library_management.service;

import org.springframework.stereotype.Service;
import com.example.library_management.dto.LibraryDto;
import com.example.library_management.entity.Library;
import com.example.library_management.mapper.LibraryMapper;
import com.example.library_management.repository.LibraryRepository;
import com.example.library_management.service.ServiceInterface.LibraryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LibraryServiceImpl implements LibraryService {
  private final LibraryRepository repository;

  @Override
  public LibraryDto createLibrary(String name) {
    Library library = new Library();
    library.setName(name);

    Library saved = repository.save(library);
    return LibraryMapper.toDto(saved);
  }

  @Override
  @Transactional
  public LibraryDto getLibraryById(Long id) {
    Library library = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Library not found with id: " + id));
    return LibraryMapper.toDto(library);
  }
}
