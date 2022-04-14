package com.example.springreactiveredis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RedisHash
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @NotNull
    private String id;

    private String title;

    private int page;

    private String isbn;

    private String description;

    private double price;

    private LocalDate publicationDate;

    private String language;
}
