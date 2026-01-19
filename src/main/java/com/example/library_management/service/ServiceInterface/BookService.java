package com.example.library_management.service.ServiceInterface;

import com.example.library_management.dto.BookDto;
import com.example.library_management.dto.CreateBookRequest;

public interface BookService {
  BookDto createBook(CreateBookRequest request);

  BookDto getBookById(Long id);
}
