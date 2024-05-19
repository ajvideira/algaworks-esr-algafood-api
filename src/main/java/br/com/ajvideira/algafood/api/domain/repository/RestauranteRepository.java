package br.com.ajvideira.algafood.api.domain.repository;

import java.util.List;

import br.com.ajvideira.algafood.api.domain.model.Restaurante;

public interface RestauranteRepository {

    List<Restaurante> findAll();

    Restaurante findById(Long id);

    Restaurante save(Restaurante restaurante);

    void delete(Restaurante restaurante);

}
