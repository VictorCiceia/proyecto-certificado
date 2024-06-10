package com.project.certified.services;

import com.project.certified.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> findAll();

    BookDto findById(String id);

    BookDto save(BookDto book);

    BookDto update(BookDto book, String id);

    void deleteById(String id);

}
