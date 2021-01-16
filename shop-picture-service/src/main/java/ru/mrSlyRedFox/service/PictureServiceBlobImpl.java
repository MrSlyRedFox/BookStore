package ru.mrSlyRedFox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;

import ru.mrSlyRedFox.persist.model.Picture;
import ru.mrSlyRedFox.persist.model.PictureData;
import ru.mrSlyRedFox.persist.repo.PictureRepository;


@Service
//@ConditionalOnProperty(name = "picture.storage.type", havingValue = "database")
public class PictureServiceBlobImpl implements PictureService {

    private final PictureRepository repository;

    @Autowired
    public PictureServiceBlobImpl(PictureRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(long id) {
        return repository.findById(id)
                // TODO перенести проверку на уровень JPQL запроса
                .map(Picture::getContentType);
    }

    @Override
    public Optional<byte[]> getPictureDataById(long id) {
        return repository.findById(id)
                // TODO перенести проверку на уровень JPQL запроса
                .map(pic -> pic.getPictureData().getData());
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        return new PictureData(picture);
    }
}
