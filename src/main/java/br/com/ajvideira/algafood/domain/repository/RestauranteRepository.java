package br.com.ajvideira.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ajvideira.algafood.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
