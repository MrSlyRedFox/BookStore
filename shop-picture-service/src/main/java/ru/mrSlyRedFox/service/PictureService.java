package ru.mrSlyRedFox.service;

import java.util.Optional;

import ru.mrSlyRedFox.persist.model.PictureData;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id);

    PictureData createPictureData(byte[] picture);
}
