package com.example.library_management.mapper;

import com.example.library_management.dto.BookDto;
import com.example.library_management.entity.Book;

public class BookMapper {
  public static BookDto toDto(Book book) {
    return new BookDto(book.getId(), book.getTitle(), book.getContent(), book.getYear(),
        book.getAuthor().getId(), book.getAuthor().getName());
  }
}
