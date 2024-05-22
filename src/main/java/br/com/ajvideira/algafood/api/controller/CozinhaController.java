package br.com.ajvideira.algafood.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ajvideira.algafood.api.model.CozinhasXmlWrapper;
import br.com.ajvideira.algafood.domain.model.Cozinha;
import br.com.ajvideira.algafood.domain.repository.CozinhaRepository;

@RequestMapping("/cozinhas")
@RestController
public class CozinhaController {

    private CozinhaRepository cozinhaRepository;

    public CozinhaController(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @GetMapping
    public List<Cozinha> getAll() {
        return cozinhaRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper getAllinXML() {
        return new CozinhasXmlWrapper(cozinhaRepository.findAll());
    }

    @GetMapping("/{cozinhaId}")
    public Cozinha getById(@PathVariable Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId);
    }

}
