package ru.mrSlyRedFox.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.mrSlyRedFox.persist.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
