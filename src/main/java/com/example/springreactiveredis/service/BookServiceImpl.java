package com.example.springreactiveredis.service;

import com.example.springreactiveredis.domain.Book;
import com.example.springreactiveredis.repository.RedisBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final RedisBookRepository bookRepository;

    @Override
    public Mono<Book> create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Flux<Book> getAll(){
        return bookRepository.getAll();
    }

    @Override
    public Mono<Book> getOne(String id){
        return bookRepository.get(id);
    }
}
