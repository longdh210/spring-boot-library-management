package com.example.library_management.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.library_management.dto.BookDto;
import com.example.library_management.dto.CreateBookRequest;
import com.example.library_management.entity.Author;
import com.example.library_management.entity.Book;
import com.example.library_management.entity.Library;
import com.example.library_management.mapper.BookMapper;
import com.example.library_management.repository.AuthorRepository;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.LibraryRepository;
import com.example.library_management.service.ServiceInterface.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final LibraryRepository libraryRepository;

  @Override
  public BookDto createBook(CreateBookRequest request) {
    Author author = authorRepository.findById(request.authorId())
        .orElseThrow(() -> new RuntimeException("Author not found with id: " + request.authorId()));

    Library library = libraryRepository.findById(request.libraryId()).orElseThrow(
        () -> new RuntimeException("Library not found with id: " + request.libraryId()));

    Book book = new Book();
    book.setTitle(request.title());
    book.setContent(request.content());
    book.setYear(request.year());
    book.setAuthor(author);
    book.setLibrary(library);

    Book saved = bookRepository.save(book);

    return BookMapper.toDto(saved);
  }

  @Override
  @Transactional
  @Cacheable("books")
  public BookDto getBookById(Long id) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

    simulateSlowService();
    return BookMapper.toDto(book);
  }

  private void simulateSlowService() {
    try {
      Thread.sleep(3000L);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
