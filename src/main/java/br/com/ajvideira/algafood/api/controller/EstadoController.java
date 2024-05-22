package br.com.ajvideira.algafood.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ajvideira.algafood.domain.model.Estado;
import br.com.ajvideira.algafood.domain.repository.EstadoRepository;

@RequestMapping("/estados")
@RestController
public class EstadoController {

    private EstadoRepository estadoRepository;

    public EstadoController(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Estado> getAll() {
        return this.estadoRepository.findAll();
    }

}
