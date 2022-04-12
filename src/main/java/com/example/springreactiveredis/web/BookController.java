package com.example.springreactiveredis.web;

import com.example.springreactiveredis.domain.Book;
import com.example.springreactiveredis.service.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> addBook(@RequestBody @Valid Book book) {
        return bookService.create(book);

    }

    @GetMapping("/book")
    public Flux<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/book/{id}")
    public Mono<Book> getBook(@PathVariable String id) {
        return bookService.getOne(id);
    }

}
