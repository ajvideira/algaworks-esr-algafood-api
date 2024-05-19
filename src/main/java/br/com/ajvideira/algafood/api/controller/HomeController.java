package br.com.ajvideira.algafood.api.controller;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ajvideira.algafood.api.domain.model.Cozinha;
import br.com.ajvideira.algafood.api.jpa.CadastroCozinha;

@RequestMapping("/")
@RestController
public class HomeController {

    private CadastroCozinha cadastroCozinha;

    public HomeController(CadastroCozinha cadastroCozinha) {
        this.cadastroCozinha = cadastroCozinha;
    }

    @GetMapping
    public String home() {
        return "OK";
    }

    @GetMapping("/cozinhas")
    public String getMethodName() {
        return cadastroCozinha.listar().stream().map(Cozinha::getNome).collect(Collectors.joining(", "));
    }

}
