package br.com.ajvideira.algafood.api.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.api.domain.model.Restaurante;
import br.com.ajvideira.algafood.api.domain.repository.RestauranteRepository;
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
    public void delete(Restaurante restaurante) {
        restaurante = findById(restaurante.getId());
        this.entityManager.remove(restaurante);
    }

}
