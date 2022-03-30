package com.example.springreactiveredis.repository;

import com.example.springreactiveredis.domain.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository {

    Mono<Book> save(Book book);

    Flux<Book> getAll();
}
