package com.example.demo;

import reactor.core.publisher.Mono;

public interface BookRepository {

    Mono<Link> save(Link link);

    Mono<Link> findByKey(String key);
}
