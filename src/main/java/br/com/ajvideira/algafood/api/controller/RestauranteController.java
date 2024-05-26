package br.com.ajvideira.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.model.Restaurante;
import br.com.ajvideira.algafood.domain.repository.RestauranteRepository;
import br.com.ajvideira.algafood.domain.service.RestauranteService;

@RequestMapping("/restaurantes")
@RestController
public class RestauranteController {

    private RestauranteRepository restauranteRepository;

    private RestauranteService restauranteService;

    public RestauranteController(RestauranteRepository restauranteRepository, RestauranteService restauranteService) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> getAll() {
        return ResponseEntity.ok(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> getById(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteRepository.findById(restauranteId);

        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Restaurante> create(@RequestBody Restaurante restaurante) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteService.save(restaurante));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> update(@PathVariable Long restauranteId,
            @RequestBody Restaurante restauranteRequest) {
        try {
            var restaurante = restauranteRepository.findById(restauranteId);

            if (restaurante != null) {
                BeanUtils.copyProperties(restauranteRequest, restaurante, "id");
                return ResponseEntity.ok(restauranteService.save(restaurante));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
