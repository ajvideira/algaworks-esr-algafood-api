package br.com.ajvideira.algafood.api.di.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import br.com.ajvideira.algafood.api.di.model.Cliente;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AtivacaoClienteService {

    private ApplicationEventPublisher eventPublisher;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
    }

}
