package br.com.ajvideira.algafood.api.di.notificacao;

import br.com.ajvideira.algafood.api.di.model.Cliente;

public interface Notificador {

    void notificar(Cliente cliente, String mensagem);

}
