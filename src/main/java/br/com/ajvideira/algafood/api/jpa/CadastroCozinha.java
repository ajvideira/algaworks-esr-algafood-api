package br.com.ajvideira.algafood.api.jpa;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ajvideira.algafood.api.domain.model.Cozinha;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Component
public class CadastroCozinha {

    private EntityManager entityManager;

    public CadastroCozinha(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Cozinha> listar() {
        return entityManager.createQuery("FROM Cozinha", Cozinha.class).getResultList();
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    public Cozinha buscar(Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    public void remover(Cozinha cozinha) {
        cozinha = this.buscar(cozinha.getId());
        entityManager.remove(cozinha);
    }

}
