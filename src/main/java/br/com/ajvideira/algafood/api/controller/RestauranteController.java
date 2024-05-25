package br.com.ajvideira.algafood.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ajvideira.algafood.domain.model.Restaurante;
import br.com.ajvideira.algafood.domain.repository.RestauranteRepository;

@RequestMapping("/restaurantes")
@RestController
public class RestauranteController {

    private RestauranteRepository restauranteRepository;

    public RestauranteController(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
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

}
