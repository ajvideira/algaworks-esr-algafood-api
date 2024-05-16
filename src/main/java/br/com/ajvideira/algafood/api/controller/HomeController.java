package br.com.ajvideira.algafood.api.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.ajvideira.algafood.api.di.model.Cliente;
import br.com.ajvideira.algafood.api.di.service.AtivacaoClienteService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class HomeController {

    private AtivacaoClienteService ativacaoClienteService;

    @GetMapping
    public String home() {
        Cliente cliente = new Cliente("Jonathan", "jonathan.videira@gmail.com", "51984416170");

        ativacaoClienteService.ativar(cliente);

        return "OK";
    }

}
