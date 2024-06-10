package com.project.certified.controller;

import com.project.certified.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.certified.services.BookService;
import com.project.certified.services.BookServiceFactory;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    public BookController() {
        this.bookService = BookServiceFactory.getBookService();
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDto> findById(@PathVariable("id") final String id) {
        return ResponseEntity.ok(this.bookService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.ok(this.bookService.findAll());
    }

    @PostMapping
    public ResponseEntity<BookDto> save(@RequestBody final BookDto bookDto) {
        return ResponseEntity.ok().body(this.bookService.save(bookDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<BookDto> update(@PathVariable("id") final String id, @RequestBody final BookDto bookDto) {
        return ResponseEntity.ok(this.bookService.update(bookDto, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") final String id) {
        this.bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
