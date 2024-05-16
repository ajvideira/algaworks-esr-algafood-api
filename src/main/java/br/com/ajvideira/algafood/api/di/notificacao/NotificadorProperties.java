package br.com.ajvideira.algafood.api.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("notificador")
public class NotificadorProperties {

    private String serverHost;
    private Integer serverPort = 25;

}
