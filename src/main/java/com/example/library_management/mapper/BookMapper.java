package com.example.library_management.mapper;

import com.example.library_management.dto.BookDto;
import com.example.library_management.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "book.author.id", target = "authorId")
    BookDto toDto(Book book);
}
