package br.com.ajvideira.algafood.api.jpa;

import java.util.Arrays;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.ajvideira.algafood.api.AlgafoodApiApplication;
import br.com.ajvideira.algafood.api.domain.model.Cozinha;
import br.com.ajvideira.algafood.api.domain.repository.CozinhaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdicionarCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinhaBrasileira = new Cozinha();
        cozinhaBrasileira.setNome("Brasileira");
        cozinhaBrasileira = cozinhaRepository.save(cozinhaBrasileira);

        Cozinha cozinhaJaponesa = new Cozinha();
        cozinhaJaponesa.setNome("Japonesa");
        cozinhaJaponesa = cozinhaRepository.save(cozinhaJaponesa);

        Arrays.asList(cozinhaBrasileira, cozinhaJaponesa).stream()
                .forEach(cozinha -> log.info("{} - {}", cozinha.getId(), cozinha.getNome()));

    }

}
