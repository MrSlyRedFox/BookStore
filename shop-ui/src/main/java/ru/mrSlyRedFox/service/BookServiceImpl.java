package ru.mrSlyRedFox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ru.mrSlyRedFox.controllers.repr.BookRepr;
import ru.mrSlyRedFox.persist.model.Book;
import ru.mrSlyRedFox.persist.repo.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<BookRepr> findById(Long id) {
        return bookRepository.findById(id).map(BookRepr::new);
    }

    public List<List<BookRepr>> findAllAndSplitBooksBy(int groupSize) {
        List<List<BookRepr>> result = new ArrayList<>();
        List<BookRepr> subList = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            subList.add(new BookRepr(book));
            if (subList.size() == groupSize) {
                result.add(subList);
                subList = new ArrayList<>();
            }
        }
        if (!subList.isEmpty()) {
            result.add(subList);
        }
        return result;
    }
}
