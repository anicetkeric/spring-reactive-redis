package com.example.springreactiveredis.web;

import com.example.springreactiveredis.domain.Book;
import com.example.springreactiveredis.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> addBook(@RequestBody @Valid Book book) {
        return bookService.create(book);

    }

    @GetMapping("/book")
    public Flux<Book> getAllBooks() {
        return bookService.getAll();
    }

}
