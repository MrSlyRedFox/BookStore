package ru.mrSlyRedFox.service;

import java.util.Optional;

import ru.mrSlyRedFox.persist.model.Book;
import ru.mrSlyRedFox.persist.model.PictureData;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id);

    PictureData createPictureData(byte[] picture);

    Optional<Book> getBookByPictureId(long id);

    // TODO перенести сюда функционал получения списка картинок

    // TODO перенести сюда функционал для удаления картинок

    void removePicture(long id);
}
