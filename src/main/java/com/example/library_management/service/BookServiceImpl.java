package com.example.library_management.service;

import com.example.library_management.entity.OutboxEvent;
import com.example.library_management.mapper.BookMapper;
import com.example.library_management.repository.OutboxEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.library_management.dto.BookDto;
import com.example.library_management.dto.CreateBookRequest;
import com.example.library_management.entity.Author;
import com.example.library_management.entity.Book;
import com.example.library_management.entity.Library;
import com.example.library_management.repository.AuthorRepository;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.LibraryRepository;
import com.example.library_management.service.ServiceInterface.BookService;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class BookServiceImpl implements BookService {
  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private LibraryRepository libraryRepository;

  @Autowired
  private OutboxEventRepository outboxEventRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  @Transactional
  public BookDto createBook(CreateBookRequest request) throws JsonProcessingException {
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

    OutboxEvent outboxEvent = new OutboxEvent();
    outboxEvent.setId(UUID.randomUUID());
    outboxEvent.setAggregateType("BOOK");
    outboxEvent.setAggregateId(saved.getId().toString());
    outboxEvent.setType("BOOK_CREATED");
    try {
      outboxEvent.setPayload(objectMapper.writeValueAsString(BookMapper.INSTANCE.toDto(book)));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize book", e);
    }
    outboxEvent.setCreatedAt(LocalDateTime.now());
    outboxEvent.setPublished(false);

    outboxEventRepository.save(outboxEvent);

    return BookMapper.INSTANCE.toDto(saved);
  }

  @Override
  @Transactional
  @Cacheable("books")
  public BookDto getBookById(Long id) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

//    simulateSlowService();
    return BookMapper.INSTANCE.toDto(book);
  }

  private void simulateSlowService() {
    try {
      Thread.sleep(3000L);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
