package com.example.Auth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
    @GetMapping("/helloWord")
    public String helloWord() {
        return "Haz iniciado su sesión con éxito";
    }
}
