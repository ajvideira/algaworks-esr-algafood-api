package br.com.ajvideira.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.domain.model.Cidade;

@Repository
public interface CidadeRepository {

    List<Cidade> findAll();

    Cidade findById(Long id);

    Cidade save(Cidade cidade);

    void delete(Cidade cidade);

}
