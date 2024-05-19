package br.com.ajvideira.algafood.api.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.ajvideira.algafood.api.AlgafoodApiApplication;
import br.com.ajvideira.algafood.api.domain.model.Cozinha;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsultarCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        cadastroCozinha.listar().stream().map(Cozinha::getNome).forEach(nome -> log.info(nome));
    }

}
