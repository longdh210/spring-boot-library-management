package com.example.library_management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private Long id;
    private String title;
    private String content;
    private Integer year;
    private Long authorId;
}