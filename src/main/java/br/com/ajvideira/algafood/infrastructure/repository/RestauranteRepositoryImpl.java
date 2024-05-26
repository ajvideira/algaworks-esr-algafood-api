package br.com.ajvideira.algafood.infrastructure.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.domain.model.Restaurante;
import br.com.ajvideira.algafood.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

    private EntityManager entityManager;

    public RestauranteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Restaurante> findAll() {
        return this.entityManager.createQuery("FROM Restaurante", Restaurante.class).getResultList();
    }

    @Override
    public Restaurante findById(Long id) {
        return this.entityManager.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public Restaurante save(Restaurante restaurante) {
        return this.entityManager.merge(restaurante);
    }

    @Transactional
    @Override
    public void delete(Long restauranteId) {
        var restaurante = findById(restauranteId);

        if (restaurante == null) {
            throw new EmptyResultDataAccessException(1);
        }

        this.entityManager.remove(restaurante);
    }

}
