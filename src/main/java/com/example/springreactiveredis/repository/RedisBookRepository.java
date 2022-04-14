package com.example.springreactiveredis.repository;

import com.example.springreactiveredis.config.ObjectMapperUtils;
import com.example.springreactiveredis.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.springreactiveredis.config.ObjectMapperUtils.BOOK_KEY;

@Repository
@RequiredArgsConstructor
public class RedisBookRepository implements BookRepository {

    private final ReactiveRedisComponent reactiveRedisComponent;

    @Override
    public Mono<Book> save(Book book) {
        return reactiveRedisComponent.set(BOOK_KEY, book.getId(), book).map(b -> book);
    }

    @Override
    public Mono<Book> get(String key) {
        return reactiveRedisComponent.get(BOOK_KEY, key).flatMap(d -> Mono.just(ObjectMapperUtils.objectMapper(d, Book.class)));
    }

    @Override
    public Flux<Book> getAll(){
        return reactiveRedisComponent.get(BOOK_KEY).map(b -> ObjectMapperUtils.objectMapper(b, Book.class))
                .collectList().flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Long> delete(String id) {
        return reactiveRedisComponent.remove(BOOK_KEY,id);
    }
}
