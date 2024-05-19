package br.com.ajvideira.algafood.api.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.api.domain.model.FormaPagamento;
import br.com.ajvideira.algafood.api.domain.repository.FormaPagamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    private EntityManager entityManager;

    public FormaPagamentoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<FormaPagamento> findAll() {
        return this.entityManager.createQuery("FROM FormaPagamento", FormaPagamento.class).getResultList();
    }

    @Override
    public FormaPagamento findById(Long id) {
        return this.entityManager.find(FormaPagamento.class, id);
    }

    @Transactional
    @Override
    public FormaPagamento save(FormaPagamento formaPagamento) {
        return this.entityManager.merge(formaPagamento);
    }

    @Transactional
    @Override
    public void delete(FormaPagamento formaPagamento) {
        formaPagamento = findById(formaPagamento.getId());
        this.entityManager.remove(formaPagamento);
    }

}
