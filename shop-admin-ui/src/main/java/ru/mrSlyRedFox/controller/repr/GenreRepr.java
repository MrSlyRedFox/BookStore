package ru.mrSlyRedFox.controller.repr;

import java.util.List;

import ru.mrSlyRedFox.persist.model.Book;

public class GenreRepr {

    private Long genre_id;

    private String nameOfTheGenre;

    private List<Book> bookList;

    public GenreRepr() {
    }

    public GenreRepr(Long genre_id, String nameOfTheGenre) {
        this.genre_id = genre_id;
        this.nameOfTheGenre = nameOfTheGenre;
    }

    public Long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Long genre_id) {
        this.genre_id = genre_id;
    }

    public String getNameOfTheGenre() {
        return nameOfTheGenre;
    }

    public void setNameOfTheGenre(String nameOfTheGenre) {
        this.nameOfTheGenre = nameOfTheGenre;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "GenreRepr{" + "genre_id=" + genre_id + ", nameOfTheGenre='" + nameOfTheGenre + '\'' + ", bookList=" + bookList + '}';
    }
}
