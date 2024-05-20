package br.com.ajvideira.algafood.api.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.api.domain.model.Estado;
import br.com.ajvideira.algafood.api.domain.repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

    private EntityManager entityManager;

    public EstadoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Estado> findAll() {
        return this.entityManager.createQuery("FROM Estado", Estado.class).getResultList();
    }

    @Override
    public Estado findById(Long id) {
        return this.entityManager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado save(Estado estado) {
        return this.entityManager.merge(estado);
    }

    @Transactional
    @Override
    public void delete(Estado estado) {
        estado = findById(estado.getId());
        this.entityManager.remove(estado);
    }

}
