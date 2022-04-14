package com.example.springreactiveredis.service;

import com.example.springreactiveredis.domain.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

    Mono<Book> create(Book book);

    Flux<Book> getAll();

    Mono<Book> getOne(String id);

    Mono<Long> deleteById(String id);
}
