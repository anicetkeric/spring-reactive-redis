package com.example.springreactiveredis.repository;

import com.example.springreactiveredis.domain.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Repository
public class RedisBookRepository implements BookRepository {

    private static final String BOOK_KEY = "BK";

    private final ReactiveRedisOperations<String, Object> redisOperations;

    public RedisBookRepository(ReactiveRedisOperations<String, Object> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    public Mono<Book> save(Book book) {
        return redisOperations.opsForHash().put(BOOK_KEY, book.getId(), book).map(b -> book);
    }

    @Override
    public Flux<Book> getAll(){
        Flux<Object> list = redisOperations.opsForHash().values(BOOK_KEY);
        return list.map(x -> new ObjectMapper().convertValue(x, Book.class))
                .collectList().flatMapMany(Flux::fromIterable);
    }
}
