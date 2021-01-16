package ru.mrSlyRedFox.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.mrSlyRedFox.persist.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
