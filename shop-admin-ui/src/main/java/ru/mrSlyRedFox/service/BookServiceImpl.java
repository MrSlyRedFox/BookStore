package ru.mrSlyRedFox.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.mrSlyRedFox.controller.repr.BookRepr;
import ru.mrSlyRedFox.error.NotFoundException;
import ru.mrSlyRedFox.persist.model.Book;
import ru.mrSlyRedFox.persist.model.Picture;
import ru.mrSlyRedFox.persist.repo.BookRepository;


@Service
@Transactional
public class BookServiceImpl implements BookService{
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;
    private final PictureService pictureService;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, PictureService pictureService) {
        this.bookRepository = bookRepository;
        this.pictureService = pictureService;
    }

    @Override
    @Transactional
    public List<BookRepr> findAll() {
        return bookRepository.findAll().stream()
                .map(BookRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<BookRepr> findById(Long id) {
        return bookRepository.findById(id).map(BookRepr::new);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(BookRepr bookRepr) throws IOException {
        Book book = (bookRepr.getBook_id() != null) ? bookRepository.findById(bookRepr.getBook_id())
                .orElseThrow(NotFoundException::new) : new Book();
        book.setTitle(bookRepr.getTitle());
        book.setAuthorList(bookRepr.getAuthorList());
        book.setGenreList(bookRepr.getGenreList());
        book.setPrice(bookRepr.getPrice());

        if (bookRepr.getNewPictures() != null) {
            for (MultipartFile newPicture : bookRepr.getNewPictures()) {
                logger.info("Product {} file {} size {} contentType {}", bookRepr.getBook_id(),
                        newPicture.getOriginalFilename(), newPicture.getSize(), newPicture.getContentType());

                if (book.getPictures() == null) {
                    book.setPictures(new ArrayList<>());
                }

                book.getPictures().add(new Picture(
                        newPicture.getOriginalFilename(),
                        newPicture.getContentType(),
                        pictureService.createPictureData(newPicture.getBytes()),
                        book
                ));
            }
        }

        bookRepository.save(book);
    }
}
