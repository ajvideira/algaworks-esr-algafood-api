package br.com.ajvideira.algafood.api.jpa;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ajvideira.algafood.api.domain.model.Cozinha;
import jakarta.persistence.EntityManager;

@Component
public class CadastroCozinha {

    private EntityManager entityManager;

    public CadastroCozinha(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Cozinha> listar() {
        return entityManager.createQuery("FROM Cozinha", Cozinha.class).getResultList();
    }

}
