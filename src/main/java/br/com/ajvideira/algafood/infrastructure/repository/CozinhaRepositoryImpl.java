package br.com.ajvideira.algafood.infrastructure.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.domain.model.Cozinha;
import br.com.ajvideira.algafood.domain.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    private EntityManager entityManager;

    public CozinhaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Cozinha> findAll() {
        return this.entityManager.createQuery("FROM Cozinha", Cozinha.class).getResultList();
    }

    @Override
    public Cozinha findById(Long id) {
        return this.entityManager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public Cozinha save(Cozinha cozinha) {
        return this.entityManager.merge(cozinha);
    }

    @Transactional
    @Override
    public void delete(Long cozinhaId) {
        var cozinha = findById(cozinhaId);

        if (cozinha == null) {
            throw new EmptyResultDataAccessException(1);
        }

        this.entityManager.remove(cozinha);
    }

}
