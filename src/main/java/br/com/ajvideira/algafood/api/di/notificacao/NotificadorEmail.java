package br.com.ajvideira.algafood.api.di.notificacao;

import org.springframework.stereotype.Component;

import br.com.ajvideira.algafood.api.di.model.Cliente;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

    private NotificadorProperties notificadorProperties;

    public NotificadorEmail(NotificadorProperties notificadorProperties) {
        this.notificadorProperties = notificadorProperties;
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        log.info("Host: {} | port: {}\n", notificadorProperties.getServerHost(),
                notificadorProperties.getServerPort());
        log.info("Notificando {} através do email {}: {}\n", cliente.getNome(), cliente.getEmail(), mensagem);
    }

}
