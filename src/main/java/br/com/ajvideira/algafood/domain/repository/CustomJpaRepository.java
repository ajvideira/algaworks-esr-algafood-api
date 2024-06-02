package br.com.ajvideira.algafood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomJpaRepository<E, I> extends JpaRepository<E, I> {

    Optional<E> findFirst();

}
