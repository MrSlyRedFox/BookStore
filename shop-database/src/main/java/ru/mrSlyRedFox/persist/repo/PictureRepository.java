package ru.mrSlyRedFox.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.mrSlyRedFox.persist.model.Picture;


public interface PictureRepository extends JpaRepository<Picture, Long> {
}
