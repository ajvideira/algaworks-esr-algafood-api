package br.com.ajvideira.algafood.infrastructure.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.domain.model.Estado;
import br.com.ajvideira.algafood.domain.repository.EstadoRepository;
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
    public void delete(Long estadoId) {
        var estado = findById(estadoId);

        if (estado == null) {
            throw new EmptyResultDataAccessException(1);
        }

        this.entityManager.remove(estado);
    }

}
