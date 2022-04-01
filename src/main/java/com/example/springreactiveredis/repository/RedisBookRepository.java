package com.example.springreactiveredis.repository;

import com.example.springreactiveredis.domain.Book;
import com.example.springreactiveredis.service.ReactiveRedisComponent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class RedisBookRepository implements BookRepository {

    private static final String BOOK_KEY = "BK";

    private final ReactiveRedisComponent reactiveRedisComponent;

    public RedisBookRepository(ReactiveRedisComponent reactiveRedisComponent) {
        this.reactiveRedisComponent = reactiveRedisComponent;
    }

    @Override
    public Mono<Book> save(Book book) {
        return reactiveRedisComponent.put(BOOK_KEY, book.getId(), book).map(b -> book);
    }

    @Override
    public Mono<Book> get(String key) {
        return reactiveRedisComponent.get(BOOK_KEY, key);
    }

    @Override
    public Flux<Book> getAll(){
        return reactiveRedisComponent.get(BOOK_KEY).map(x -> new ObjectMapper().convertValue(x, Book.class))
                .collectList().flatMapMany(Flux::fromIterable);
    }
}
