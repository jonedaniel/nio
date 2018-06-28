package com.example.reactiveweb.service;

import com.example.reactiveweb.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<User> save(User user);

    Mono<Long> deleteByUsername(String username);

    Mono<User> findByUsername(String username);

    Flux<User> findAll();
}
