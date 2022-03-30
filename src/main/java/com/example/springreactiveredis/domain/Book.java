package com.example.springreactiveredis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
