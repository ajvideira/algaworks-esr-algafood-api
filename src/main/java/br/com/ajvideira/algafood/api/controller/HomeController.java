package br.com.ajvideira.algafood.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@RestController
public class HomeController {

    @GetMapping
    public String getMethodName() {
        return new String("Hello world!");
    }

}
