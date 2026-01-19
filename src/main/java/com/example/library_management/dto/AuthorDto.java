package com.example.library_management.dto;

import java.time.LocalDate;

public record AuthorDto(Long id, String name, LocalDate dayOfBirth) {

}
