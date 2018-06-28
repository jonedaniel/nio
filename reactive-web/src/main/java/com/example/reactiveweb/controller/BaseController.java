package com.example.reactiveweb.controller;

import com.example.reactiveweb.entity.User;
import com.example.reactiveweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/user")
public class BaseController {
    @Autowired
    private IUserService userService1;

    @PostMapping("")
    public Mono<User> save(User user) {
        return this.userService1.save(user);
    }

    @DeleteMapping("/{username}")
    public Mono<Long> deleteByUsername(@PathVariable String username) {
        return this.userService1.deleteByUsername(username);
    }

    @GetMapping("/{username}")
    public Mono<User> findByUsername(@PathVariable String username) {
        return this.userService1.findByUsername(username);
    }

    @GetMapping(value = "",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> findAll() {
        return this.userService1.findAll().delayElements(Duration.ofSeconds(1));
    }

}
