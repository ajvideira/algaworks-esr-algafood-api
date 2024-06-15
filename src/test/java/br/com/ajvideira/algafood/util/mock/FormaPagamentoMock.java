package br.com.ajvideira.algafood.util.mock;

import java.util.List;

import br.com.ajvideira.algafood.domain.model.FormaPagamento;

public class FormaPagamentoMock {

    public static FormaPagamento mock(Long formaPagamentoId) {
        return new FormaPagamento(formaPagamentoId, "Cozinha " + formaPagamentoId);
    }

    public static FormaPagamento mockForInsert() {
        return new FormaPagamento(null, "New Cozinha");
    }

    public static FormaPagamento mockForUpdate(Long formaPagamentoId) {
        return new FormaPagamento(formaPagamentoId, "Cozinha " + formaPagamentoId + " updated");
    }

    public static FormaPagamento mockForUpdateWithoutId(Long formaPagamentoId) {
        return new FormaPagamento(null, "Cozinha " + formaPagamentoId + " updated");
    }

    public static List<FormaPagamento> mockList() {
        return List.of(mock(1L), mock(2L), mock(3L));
    }

    public static FormaPagamento mockForSaveInOtherEntity(Long formaPagamentoId) {
        return new FormaPagamento(formaPagamentoId, null);
    }

}
