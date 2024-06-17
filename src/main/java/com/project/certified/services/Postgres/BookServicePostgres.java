package com.project.certified.services.Postgres;

import com.project.certified.dto.BookDto;
import com.project.certified.entity.Postgres.BookEntity;
import com.project.certified.entity.Postgres.LoanEntity;
import com.project.certified.exception.ResourceNotFoundException;
import com.project.certified.repository.Postgres.BookRepositoryPostgres;
import com.project.certified.repository.Postgres.LoanRepositoryPostgres;
import com.project.certified.services.BookService;
import com.project.certified.services.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServicePostgres implements BookService {

    @Autowired
    private BookRepositoryPostgres bookRepositoryPostgres;

    @Autowired
    private BookMapper mapper;

    @Autowired
    private LoanRepositoryPostgres loanRepository;

    @Override
    public List<BookDto> findAll() {
        return bookRepositoryPostgres.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findById(String id) {
        return bookRepositoryPostgres.findById(id)
                .map(this.mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
    }

    @Override
    public BookDto save(BookDto bookDto) {
        final BookEntity book = mapper.toEntityPostgre(bookDto);
        final BookEntity savedBook = bookRepositoryPostgres.save(book);
        savedBook.setReserved(false);
        return mapper.toDto(savedBook);
    }

    @Override
    public BookDto update(BookDto bookDto, String id) {
        final BookEntity book = bookRepositoryPostgres.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        final BookEntity updatedBook = bookRepositoryPostgres.save(book);
        return mapper.toDto(updatedBook);
    }

    @Override
    public void deleteById(String id) {
        final BookEntity book = bookRepositoryPostgres.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        // Borrando todos sus prestamos
        final List<LoanEntity> loans = loanRepository.findAllByBookId(book.getId());
        for (LoanEntity loan : loans) {
            loanRepository.deleteById(loan.getId());
        }
        bookRepositoryPostgres.delete(book);
    }

}
