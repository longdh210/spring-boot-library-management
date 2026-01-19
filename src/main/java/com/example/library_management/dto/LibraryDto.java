package com.example.library_management.dto;

import java.util.List;

public record LibraryDto(Long id, String name, List<BookDto> books) {

}
