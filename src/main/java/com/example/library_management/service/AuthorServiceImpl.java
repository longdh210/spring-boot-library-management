package com.example.library_management.service;

import java.time.LocalDate;
import org.springframework.stereotype.Service;
import com.example.library_management.dto.AuthorDto;
import com.example.library_management.entity.Author;
import com.example.library_management.mapper.AuthorMapper;
import com.example.library_management.repository.AuthorRepository;
import com.example.library_management.service.ServiceInterface.AuthorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {
  private final AuthorRepository repository;

  @Override
  public AuthorDto createAuthor(AuthorDto dto) {
    Author author = new Author();
    author.setName(dto.getName());
    author.setDayOfBirth(dto.getDayOfBirth());

    Author saved = repository.save(author);
    return AuthorMapper.INSTANCE.toDto(saved);
  }

  @Override
  @Transactional
  public AuthorDto getAuthorById(Long id) {
    Author author = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    return AuthorMapper.INSTANCE.toDto(author);
  }
}
