package com.example.library_management.dto;

public record CreateBookRequest(String title, String content, Integer year, Long authorId,
    Long libraryId) {
}
