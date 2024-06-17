package com.project.certified.services.Mongo;

import com.project.certified.dto.BookDto;
import com.project.certified.entity.Mongo.BookEntity;
import com.project.certified.entity.Mongo.LoanEntity;
import com.project.certified.exception.ResourceNotFoundException;
import com.project.certified.repository.Mongo.BookRepositoryMongo;
import com.project.certified.repository.Mongo.LoanRepositoryMongo;
import com.project.certified.services.BookService;
import com.project.certified.services.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceMongo implements BookService {

    @Autowired
    private BookRepositoryMongo bookRepositoryMongo;

    @Autowired
    private BookMapper mapper;

    @Autowired
    private LoanRepositoryMongo loanRepository;

    @Override
    public List<BookDto> findAll() {
        return bookRepositoryMongo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findById(final String id) {
        return bookRepositoryMongo.findById(id)
                .map(this.mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
    }

    @Override
    public BookDto save(final BookDto bookDto) {
        final BookEntity book = mapper.toEntityMongo(bookDto);
        book.setReserved(false);
        final BookEntity savedBook = bookRepositoryMongo.save(book);
        return mapper.toDto(savedBook);
    }

    @Override
    public BookDto update(final BookDto bookDto, final String id) {
        final BookEntity book = bookRepositoryMongo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        final BookEntity updatedBook = bookRepositoryMongo.save(book);
        return mapper.toDto(updatedBook);
    }

    @Override
    public void deleteById(final String id) {
        final BookEntity book = bookRepositoryMongo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        // Borrando todos sus prestamos
        final List<LoanEntity> loans = loanRepository.findAllByBookId(book.getId());
        for (LoanEntity loan : loans) {
            loanRepository.deleteById(loan.getId());
        }
        bookRepositoryMongo.delete(book);
    }
}