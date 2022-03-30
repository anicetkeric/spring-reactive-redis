package com.example.springreactiveredis.service;

import com.example.springreactiveredis.domain.Book;
import com.example.springreactiveredis.repository.RedisBookRepository;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final RedisBookRepository bookRepository;

    public BookService(RedisBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Mono<Book> create(Book book) {
        return bookRepository.save(book);
    }

    public Flux<Book> getAll(){
        return bookRepository.getAll();
    }
}
