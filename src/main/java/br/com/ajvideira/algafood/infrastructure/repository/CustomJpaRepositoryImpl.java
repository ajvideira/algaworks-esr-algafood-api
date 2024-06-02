package br.com.ajvideira.algafood.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import br.com.ajvideira.algafood.domain.repository.CustomJpaRepository;
import jakarta.persistence.EntityManager;

public class CustomJpaRepositoryImpl<T, I> extends SimpleJpaRepository<T, I> implements CustomJpaRepository<T, I> {

    private EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> findFirst() {
        return Optional.ofNullable(entityManager.createQuery("FROM " + getDomainClass().getName(), getDomainClass())
                .setMaxResults(1).getSingleResult());
    }

}
