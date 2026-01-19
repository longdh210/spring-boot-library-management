package com.example.library_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.library_management.dto.AuthorDto;
import com.example.library_management.service.ServiceInterface.AuthorService;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
  private final AuthorService authorService;

  @PostMapping()
  public AuthorDto create(@RequestBody AuthorDto dto) {
    return authorService.createAuthor(dto);
  }

  @GetMapping("/{id}")
  public AuthorDto getById(@PathVariable Long id) {
    return authorService.getAuthorById(id);
  }
}
