package com.example.library_management.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LibraryDto {
    private Long id;
    private String name;
    private List<BookDto> books;
}
