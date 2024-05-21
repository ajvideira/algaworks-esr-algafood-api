package br.com.ajvideira.algafood.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.domain.model.Permissao;
import br.com.ajvideira.algafood.domain.repository.PermissaoRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {

    private EntityManager entityManager;

    public PermissaoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Permissao> findAll() {
        return this.entityManager.createQuery("FROM Permissao", Permissao.class).getResultList();
    }

    @Override
    public Permissao findById(Long id) {
        return this.entityManager.find(Permissao.class, id);
    }

    @Transactional
    @Override
    public Permissao save(Permissao permissao) {
        return this.entityManager.merge(permissao);
    }

    @Transactional
    @Override
    public void delete(Permissao permissao) {
        permissao = findById(permissao.getId());
        this.entityManager.remove(permissao);
    }

}
