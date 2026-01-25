package com.example.library_management.service.Processor;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import com.example.library_management.dto.AuthorDto;

public class AuthorItemProcessor implements ItemProcessor<AuthorDto, AuthorDto> {
  private static final Logger log = LoggerFactory.getLogger(AuthorItemProcessor.class);

  @Override
  public AuthorDto process(final AuthorDto author) {
    final Long id = author.id();
    final String name = author.name().toUpperCase();
    final LocalDate dayOfBirth = author.dayOfBirth();

    final AuthorDto transformedAuthor = new AuthorDto(id, name, dayOfBirth);

    log.info("Converting ({}) into ({})", author, transformedAuthor);

    return transformedAuthor;
  }
}
