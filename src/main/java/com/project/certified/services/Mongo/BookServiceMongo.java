package com.project.certified.services.Mongo;

import com.project.certified.dto.BookDto;
import com.project.certified.repository.Mongo.BookRepositoryMongo;
import com.project.certified.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceMongo implements BookService {

    @Autowired
    private BookRepositoryMongo bookRepository;

    @Override
    public List<BookDto> findAll() {
        return null;
    }

    @Override
    public BookDto findById(String id) {
        return null;
    }

    @Override
    public BookDto save(BookDto book) {
        return null;
    }

    @Override
    public BookDto update(BookDto book, String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
}