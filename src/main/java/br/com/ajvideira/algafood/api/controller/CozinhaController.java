package br.com.ajvideira.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ajvideira.algafood.domain.exception.EntityInUseException;
import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.model.Cozinha;
import br.com.ajvideira.algafood.domain.repository.CozinhaRepository;
import br.com.ajvideira.algafood.domain.service.CozinhaService;

@RequestMapping("/cozinhas")
@RestController
public class CozinhaController {

    private CozinhaRepository cozinhaRepository;

    private CozinhaService cozinhaService;

    public CozinhaController(CozinhaRepository cozinhaRepository, CozinhaService cozinhaService) {
        this.cozinhaRepository = cozinhaRepository;
        this.cozinhaService = cozinhaService;
    }

    @GetMapping
    public ResponseEntity<List<Cozinha>> getAll() {
        return ResponseEntity.ok(cozinhaRepository.findAll());
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> getById(@PathVariable Long cozinhaId) {
        return ResponseEntity.of(cozinhaRepository.findById(cozinhaId));
    }

    @PostMapping
    public ResponseEntity<Cozinha> create(@RequestBody Cozinha cozinha) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaService.save(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> update(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinhaRequest) {
        var cozinha = cozinhaRepository.findById(cozinhaId).orElse(null);

        if (cozinha != null) {
            BeanUtils.copyProperties(cozinhaRequest, cozinha, "id");
            return ResponseEntity.ok(cozinhaService.save(cozinha));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<String> delete(@PathVariable Long cozinhaId) {
        try {
            cozinhaService.delete(cozinhaId);
            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
