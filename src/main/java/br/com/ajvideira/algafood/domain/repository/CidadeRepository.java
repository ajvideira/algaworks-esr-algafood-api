package br.com.ajvideira.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.domain.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
