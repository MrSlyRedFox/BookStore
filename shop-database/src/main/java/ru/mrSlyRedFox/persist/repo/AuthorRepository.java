package ru.mrSlyRedFox.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.mrSlyRedFox.persist.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
