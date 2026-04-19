package com.example.library_management.service.ServiceInterface;

import com.example.library_management.dto.BookDto;
import com.example.library_management.dto.CreateBookRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BookService {
  BookDto createBook(CreateBookRequest request) throws JsonProcessingException;

  BookDto getBookById(Long id);
}
