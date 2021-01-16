package ru.mrSlyRedFox.service;

import java.util.List;
import java.util.Optional;

import ru.mrSlyRedFox.controller.repr.UserRepr;

public interface UserService {

    void save(UserRepr userRepr);

    List<UserRepr> findAll();

    Optional<UserRepr> findById(Long id);

    void delete(Long id);
}
