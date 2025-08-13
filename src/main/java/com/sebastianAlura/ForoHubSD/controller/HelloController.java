package com.sebastianAlura.ForoHubSD.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String helloInicial() {
        return "Hola Sebastian ❤️, tu puedes No te rindas! 🌎😎";
    }
}
