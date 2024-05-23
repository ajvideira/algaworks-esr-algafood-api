package br.com.ajvideira.algafood.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Cozinha>> getAll() {
        return ResponseEntity.ok(cozinhaRepository.findAll());
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<CozinhasXmlWrapper> getAllinXML() {
        return ResponseEntity.ok(new CozinhasXmlWrapper(cozinhaRepository.findAll()));
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> getById(@PathVariable Long cozinhaId) {
        var cozinha = cozinhaRepository.findById(cozinhaId);
        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
