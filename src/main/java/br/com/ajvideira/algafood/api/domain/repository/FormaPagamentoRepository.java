package br.com.ajvideira.algafood.api.domain.repository;

import java.util.List;

import br.com.ajvideira.algafood.api.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

    List<FormaPagamento> findAll();

    FormaPagamento findById(Long id);

    FormaPagamento save(FormaPagamento formaPagamento);

    void delete(FormaPagamento formaPagamento);

}
