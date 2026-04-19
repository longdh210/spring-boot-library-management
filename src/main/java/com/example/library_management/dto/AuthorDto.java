package com.example.library_management.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthorDto {
    private Long id;
    private String name;
    private LocalDate dayOfBirth;
}
