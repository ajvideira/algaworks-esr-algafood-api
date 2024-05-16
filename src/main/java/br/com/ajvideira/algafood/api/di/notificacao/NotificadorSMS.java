package br.com.ajvideira.algafood.api.di.notificacao;

import org.springframework.stereotype.Component;

import br.com.ajvideira.algafood.api.di.model.Cliente;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorSMS implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        log.info("Notificando {} através do SMS {}: {}", cliente.getNome(), cliente.getTelefone(), mensagem);
    }

}
