package ru.mrSlyRedFox.controller.repr;

import java.time.LocalDate;
import java.util.List;

import ru.mrSlyRedFox.persist.model.Book;

public class AuthorRepr {

    private Long author_id;

    private String firstname;

    private String lastname;

    private LocalDate birthdate;

    private String email;

    private String phone;

    private List<Book> bookList;


    public AuthorRepr() {
    }

    public AuthorRepr(Long author_id, String firstname, String lastname, LocalDate birthdate, String email, String phone, List<Book> bookList) {
        this.author_id = author_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.bookList = bookList;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "AuthorRepr{" + "author_id=" + author_id + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", birthdate=" + birthdate + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", bookList=" + bookList + '}';
    }
}
