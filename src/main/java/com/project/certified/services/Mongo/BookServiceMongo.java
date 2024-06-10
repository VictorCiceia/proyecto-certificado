package com.project.certified.services.Mongo;

import com.project.certified.dto.BookDto;
import com.project.certified.entity.Mongo.BookEntity;
import com.project.certified.exception.ResourceNotFoundException;
import com.project.certified.repository.Mongo.BookRepositoryMongo;
import com.project.certified.services.BookService;
import com.project.certified.services.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceMongo implements BookService {

    @Autowired
    private BookRepositoryMongo bookRepository;

    @Autowired
    private BookMapper mapper;

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findById(String id) {
        return bookRepository.findById(id)
                .map(this.mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
    }

    @Override
    public BookDto save(BookDto bookDto) {
        final BookEntity book = mapper.toEntityMongo(bookDto);
        final BookEntity savedBook = bookRepository.save(book);
        return mapper.toDto(savedBook);
    }

    @Override
    public BookDto update(BookDto bookDto, String id) {
        final BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        final BookEntity updatedBook = bookRepository.save(book);
        return mapper.toDto(updatedBook);
    }

    @Override
    public void deleteById(String id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book", "id", id);
        }
        bookRepository.deleteById(id);
    }
}