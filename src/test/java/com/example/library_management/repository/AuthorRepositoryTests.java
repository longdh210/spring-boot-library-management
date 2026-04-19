package com.example.library_management.repository;

import com.example.library_management.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
class AuthorRepositoryTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void shouldSaveAuthor() {
        Author author = new Author();
        author.setName("George Orwell");
        author.setDayOfBirth(LocalDate.of(1903, 6, 25));

        Author saved = authorRepository.save(author);

        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void shouldGetAllAuthors() {
        Author author1 = new Author();
        author1.setName("George Orwell");
        author1.setDayOfBirth(LocalDate.of(1991, 6, 25));

        Author author2 = new Author();
        author2.setName("Harry Potter");
        author2.setDayOfBirth(LocalDate.of(1993, 6, 25));

        authorRepository.save(author1);
        authorRepository.save(author2);

        List<Author> authors = authorRepository.findAll();

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(3);
    }

    @Test
    void shouldGetAuthorByName() {
        Author author = new Author();
        author.setName("Test find by name");
        author.setDayOfBirth(LocalDate.of(1991, 6, 25));

        authorRepository.save(author);

        Author authorSaved = authorRepository.findByName("Test find by name").get();

        assertThat(authorSaved).isNotNull();
    }
}