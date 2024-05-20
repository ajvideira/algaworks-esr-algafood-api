package br.com.ajvideira.algafood.api.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.api.domain.model.Estado;

@Repository
public interface EstadoRepository {

    List<Estado> findAll();

    Estado findById(Long id);

    Estado save(Estado estado);

    void delete(Estado estado);

}
