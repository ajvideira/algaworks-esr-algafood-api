package br.com.ajvideira.algafood.api.domain.repository;

import java.util.List;

import br.com.ajvideira.algafood.api.domain.model.Cozinha;

public interface CozinhaRepository {

    List<Cozinha> findAll();

    Cozinha findById(Long id);

    Cozinha save(Cozinha cozinha);

    void delete(Cozinha cozinha);

}
