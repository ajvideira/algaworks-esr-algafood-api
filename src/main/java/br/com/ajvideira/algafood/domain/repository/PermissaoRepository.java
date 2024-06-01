package br.com.ajvideira.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ajvideira.algafood.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
