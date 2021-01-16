package ru.mrSlyRedFox.persist.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genres_tbl")
public class Genre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long genre_id;

    @Column(name = "name_Of_The_Genre")
    private String name_Of_The_Genre;

    @ManyToMany
    @JoinTable(name = "genres_books_tbl",
    joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> bookList;


    public Genre() {
    }

    public Genre(Long genre_id, String name_Of_The_Genre) {
        this.genre_id = genre_id;
        this.name_Of_The_Genre = name_Of_The_Genre;
    }

    public Long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Long genre_id) {
        this.genre_id = genre_id;
    }

    public String getName_Of_The_Genre() {
        return name_Of_The_Genre;
    }

    public void setName_Of_The_Genre(String name_Of_The_Genre) {
        this.name_Of_The_Genre = name_Of_The_Genre;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(genre_id, genre.genre_id) && Objects.equals(name_Of_The_Genre, genre.name_Of_The_Genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre_id, name_Of_The_Genre);
    }

    @Override
    public String toString() {
        return name_Of_The_Genre + "\n";
    }
}
