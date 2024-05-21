package br.com.ajvideira.algafood.api.domain.repository;

import java.util.List;

import br.com.ajvideira.algafood.api.domain.model.Permissao;

public interface PermissaoRepository {

    List<Permissao> findAll();

    Permissao findById(Long id);

    Permissao save(Permissao permissao);

    void delete(Permissao permissao);

}
