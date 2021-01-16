package ru.mrSlyRedFox.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import ru.mrSlyRedFox.controller.repr.BookRepr;


public interface BookService {
    List<BookRepr> findAll();

    Optional<BookRepr> findById(Long id);

    void deleteById(Long id);

    void save(BookRepr book) throws IOException;
}
