package com.example.reactiveweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class BaseController {
    @GetMapping("/hello")
    public Mono<String> helloReactive() {
        return Mono.just("hello spring reactive web");
    }
}
