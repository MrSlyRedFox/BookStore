package ru.mrSlyRedFox.controller.repr;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import ru.mrSlyRedFox.controller.repo.PictureRepr;
import ru.mrSlyRedFox.persist.model.Author;
import ru.mrSlyRedFox.persist.model.Book;
import ru.mrSlyRedFox.persist.model.Genre;

public class BookRepr implements Serializable {

    private Long book_id;

    private String title;

    private String description;

    private BigDecimal price;

    private List<Author> authorList;

    private List<Genre> genreList;

    private List<PictureRepr> pictures;

    private MultipartFile[] newPictures;

    public BookRepr() {
    }

    public BookRepr(Book book) {
        this.book_id = book.getBook_id();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.price = book.getPrice();
        this.authorList = book.getAuthorList();
        this.genreList = book.getGenreList();
        this.pictures = book.getPictures().stream()
                .map(PictureRepr::new)
                .collect(Collectors.toList());
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public List<PictureRepr> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureRepr> pictures) {
        this.pictures = pictures;
    }

    public MultipartFile[] getNewPictures() {
        return newPictures;
    }

    public void setNewPictures(MultipartFile[] newPictures) {
        this.newPictures = newPictures;
    }

    @Override
    public String toString() {
        return "BookRepr{" + "book_id=" + book_id + ", title='" + title + '\'' + ", description='" + description + '\'' + ", price=" + price + '}';
    }
}
