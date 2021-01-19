package ru.mrSlyRedFox.controllers.repr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import ru.mrSlyRedFox.persist.model.Author;
import ru.mrSlyRedFox.persist.model.Book;
import ru.mrSlyRedFox.persist.model.Genre;
import ru.mrSlyRedFox.persist.model.Picture;

public class BookRepr implements Serializable {

    private Long id;

    private String name;

    private BigDecimal price;

    private List<Author> authorsName;

    private List<Genre> genreName;

    private List<Long> pictureIds;

    private Long mainPictureId;

    public BookRepr() {
    }

    public BookRepr(Book book) {
        this.id = book.getBook_id();
        this.name = book.getTitle();
        this.price = book.getPrice();
        this.authorsName = book.getAuthorList();
        this.genreName = book.getGenreList();
        this.pictureIds = book.getPictures().stream()
                .map(Picture::getId)
                .collect(Collectors.toList());
        this.mainPictureId = pictureIds.isEmpty() ? -1 : pictureIds.get(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Author> getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(List<Author> authorsName) {
        this.authorsName = authorsName;
    }

    public List<Genre> getGenreName() {
        return genreName;
    }

    public void setGenreName(List<Genre> genreName) {
        this.genreName = genreName;
    }

    public List<Long> getPictureIds() {
        return pictureIds;
    }

    public void setPictureIds(List<Long> pictureIds) {
        this.pictureIds = pictureIds;
    }

    public Long getMainPictureId() {
        return mainPictureId;
    }

    public void setMainPictureId(Long mainPictureId) {
        this.mainPictureId = mainPictureId;
    }
}
