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
import br.com.ajvideira.algafood.domain.model.Estado;
import br.com.ajvideira.algafood.domain.repository.EstadoRepository;
import br.com.ajvideira.algafood.domain.service.EstadoService;

@RequestMapping("/estados")
@RestController
public class EstadoController {

    private EstadoRepository estadoRepository;

    private EstadoService estadoService;

    public EstadoController(EstadoRepository estadoRepository, EstadoService estadoService) {
        this.estadoRepository = estadoRepository;
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<List<Estado>> getAll() {
        return ResponseEntity.ok(this.estadoRepository.findAll());
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> getById(@PathVariable Long estadoId) {
        return ResponseEntity.of(estadoRepository.findById(estadoId));
    }

    @PostMapping
    public ResponseEntity<Estado> create(@RequestBody Estado estado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.save(estado));
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> update(@PathVariable Long estadoId, @RequestBody Estado estadoRequest) {
        var estado = estadoRepository.findById(estadoId).orElse(null);

        if (estado != null) {
            BeanUtils.copyProperties(estadoRequest, estado, "id");
            return ResponseEntity.ok(estadoService.save(estado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<String> delete(@PathVariable Long estadoId) {
        try {
            estadoService.delete(estadoId);
            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
