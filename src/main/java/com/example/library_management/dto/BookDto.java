package com.example.library_management.dto;

public record BookDto(Long id, String title, String content, Integer year, Long authorId,
    String AuthorName) {
}
