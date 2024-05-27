package br.com.ajvideira.algafood.infrastructure.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.domain.model.Cidade;
import br.com.ajvideira.algafood.domain.repository.CidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

    private EntityManager entityManager;

    public CidadeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Cidade> findAll() {
        return this.entityManager.createQuery("FROM Cidade", Cidade.class).getResultList();
    }

    @Override
    public Cidade findById(Long id) {
        return this.entityManager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public Cidade save(Cidade cidade) {
        return this.entityManager.merge(cidade);
    }

    @Transactional
    @Override
    public void delete(Long cidadeId) {
        var cidade = findById(cidadeId);

        if (cidade == null) {
            throw new EmptyResultDataAccessException(1);
        }

        this.entityManager.remove(cidade);
    }

}
