package com.project.certified.services.postgres;

import com.project.certified.dto.BookDto;
import com.project.certified.entity.Postgres.BookEntity;
import com.project.certified.exception.ResourceNotFoundException;
import com.project.certified.repository.Postgres.BookRepositoryPostgres;
import com.project.certified.repository.Postgres.LoanRepositoryPostgres;
import com.project.certified.services.Postgres.BookServicePostgres;
import com.project.certified.services.mapper.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServicePostgresTest {

    @Mock
    private BookRepositoryPostgres bookRepositoryPostgres;

    @Mock
    private BookMapper mapper;

    @Mock
    private LoanRepositoryPostgres loanRepository;

    @InjectMocks
    private BookServicePostgres bookService;

    private BookEntity bookEntity;

    private BookDto bookDto;

    private final String ID = "666f8efc60e4f879ba1a2888";

    @BeforeEach
    void setUp() {
        bookEntity = new BookEntity();
        bookEntity.setId(ID);
        bookEntity.setTitle("Title test");
        bookEntity.setAuthor("Juan Perez");
        bookEntity.setReserved(false);

        bookDto = new BookDto();
        bookDto.setId(ID);
        bookDto.setTitle("Title test");
        bookDto.setAuthor("Juan Perez");
        bookDto.setReserved(false);
    }

    @Test
    void findAllShouldReturnsSuccessfully() {
        when(bookRepositoryPostgres.findAll()).thenReturn(Arrays.asList(bookEntity));
        when(mapper.toDto(bookEntity)).thenReturn(bookDto);
        final List<BookDto> books = bookService.findAll();

        assertNotNull(books);
        assertEquals(1, books.size());
        verify(bookRepositoryPostgres, times(1)).findAll();
        verify(mapper, times(1)).toDto(bookEntity);
    }

    @Test
    void findByIdShouldReturnsSuccessfully() {
        when(bookRepositoryPostgres.findById(ID)).thenReturn(Optional.of(bookEntity));
        when(mapper.toDto(bookEntity)).thenReturn(bookDto);
        final BookDto foundBook = bookService.findById(ID);

        assertNotNull(foundBook);
        assertEquals(ID, foundBook.getId());
        assertEquals("Title test", foundBook.getTitle());
        verify(bookRepositoryPostgres, times(1)).findById(ID);
        verify(mapper, times(1)).toDto(bookEntity);
    }

    @Test
    void findByIdInvalidIdShouldReturnsException() {
        when(bookRepositoryPostgres.findById(ID)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookService.findById(ID));
        verify(bookRepositoryPostgres, times(1)).findById(ID);
    }

    @Test
    void saveShouldReturnsSuccessfully() {
        when(mapper.toEntityPostgre(bookDto)).thenReturn(bookEntity);
        when(bookRepositoryPostgres.save(bookEntity)).thenReturn(bookEntity);
        when(mapper.toDto(bookEntity)).thenReturn(bookDto);

        BookDto savedBook = bookService.save(bookDto);

        assertNotNull(savedBook);
        assertEquals(ID, savedBook.getId());
        assertEquals("Title test", savedBook.getTitle());
        verify(bookRepositoryPostgres, times(1)).save(bookEntity);
        verify(mapper, times(1)).toEntityPostgre(bookDto);
        verify(mapper, times(1)).toDto(bookEntity);
    }

    @Test
    void updateShouldReturnsSuccessfully() {
        when(bookRepositoryPostgres.findById(ID)).thenReturn(Optional.of(bookEntity));
        when(bookRepositoryPostgres.save(bookEntity)).thenReturn(bookEntity);
        when(mapper.toDto(bookEntity)).thenReturn(bookDto);
        bookDto.setTitle("Title");
        bookDto.setAuthor("Author");
        final BookDto updatedBook = bookService.update(bookDto, ID);

        assertNotNull(updatedBook);
        assertEquals(ID, updatedBook.getId());
        assertEquals("Title", updatedBook.getTitle());
        assertEquals("Author", updatedBook.getAuthor());
        verify(bookRepositoryPostgres, times(1)).findById(ID);
        verify(bookRepositoryPostgres, times(1)).save(bookEntity);
        verify(mapper, times(1)).toDto(bookEntity);
    }

    @Test
    void updateInvalidIdShouldReturnsExceptio() {
        when(bookRepositoryPostgres.findById(ID)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookService.update(bookDto, ID));
        verify(bookRepositoryPostgres, times(1)).findById(ID);
    }

    @Test
    void deleteByIdInvalidIdShouldReturnsException() {
        when(bookRepositoryPostgres.findById(ID)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookService.deleteById(ID));
        verify(bookRepositoryPostgres, times(1)).findById(ID);
    }
}
