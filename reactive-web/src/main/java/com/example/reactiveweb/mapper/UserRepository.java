package com.example.reactiveweb.mapper;

import com.example.reactiveweb.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, String> {  // 1
    Mono<User> findByUsername(String username);     // 2
    Mono<Long> deleteByUsername(String username);
}
