package com.example.library_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.library_management.dto.LibraryDto;
import com.example.library_management.service.ServiceInterface.LibraryService;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/libraries")
public class LibraryController {
  @Autowired
  private LibraryService libraryService;

  @PostMapping()
  public LibraryDto create(@RequestBody String name) {
    return libraryService.createLibrary(name);
  }

  @GetMapping("/{id}")
  public LibraryDto getById(@PathVariable Long id) {
    return libraryService.getLibraryById(id);
  }
}
