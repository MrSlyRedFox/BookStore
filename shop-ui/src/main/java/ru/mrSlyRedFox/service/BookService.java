package ru.mrSlyRedFox.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import ru.mrSlyRedFox.controllers.repr.BookRepr;


public interface BookService extends Serializable {

    Optional<BookRepr> findById(Long id);

    List<List<BookRepr>> findAllAndSplitBooksBy(int groupSize);
}
