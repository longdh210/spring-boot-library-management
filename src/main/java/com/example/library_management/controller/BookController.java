package com.example.library_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.library_management.dto.BookDto;
import com.example.library_management.dto.CreateBookRequest;
import com.example.library_management.service.ServiceInterface.BookService;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;

  @PostMapping()
  public BookDto create(@RequestBody CreateBookRequest request) {
    return bookService.createBook(request);
  }

  @GetMapping("/{id}")
  public BookDto getById(@PathVariable Long id) {
    return bookService.getBookById(id);
  }
}
