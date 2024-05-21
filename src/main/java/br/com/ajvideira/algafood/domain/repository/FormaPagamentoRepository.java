package br.com.ajvideira.algafood.domain.repository;

import java.util.List;

import br.com.ajvideira.algafood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

    List<FormaPagamento> findAll();

    FormaPagamento findById(Long id);

    FormaPagamento save(FormaPagamento formaPagamento);

    void delete(FormaPagamento formaPagamento);

}
