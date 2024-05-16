package br.com.ajvideira.algafood.api.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import br.com.ajvideira.algafood.api.di.notificacao.NivelUrgencia;
import br.com.ajvideira.algafood.api.di.notificacao.Notificador;
import br.com.ajvideira.algafood.api.di.notificacao.TipoDoNotificador;
import br.com.ajvideira.algafood.api.di.service.ClienteAtivadoEvent;

@Service
public class NotificacaoService {

    private Notificador notificador;

    public NotificacaoService(@TipoDoNotificador(NivelUrgencia.URGENTE) Notificador notificador) {
        this.notificador = notificador;
    }

    @EventListener
    public void listenClienteAtivado(ClienteAtivadoEvent event) {
        notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo!");
    }

}
