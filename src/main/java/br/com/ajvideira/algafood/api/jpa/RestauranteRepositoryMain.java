package br.com.ajvideira.algafood.api.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.ajvideira.algafood.api.AlgafoodApiApplication;
import br.com.ajvideira.algafood.api.domain.model.Cozinha;
import br.com.ajvideira.algafood.api.domain.model.Restaurante;
import br.com.ajvideira.algafood.api.domain.repository.CozinhaRepository;
import br.com.ajvideira.algafood.api.domain.repository.RestauranteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestauranteRepositoryMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        restauranteRepository.findAll().stream().map(Restaurante::getNome).forEach(nome -> log.info(nome));

        Restaurante restaurantePorId = restauranteRepository.findById(1L);

        log.info("Restaurante encontrado: {} - Cozinha {}", restaurantePorId.getNome(),
                restaurantePorId.getCozinha().getNome());

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Al Nur");
        restaurante.setTaxaFrete(BigDecimal.ZERO);

        Cozinha cozinha = cozinhaRepository.findById(2L);
        restaurante.setCozinha(cozinha);

        restaurante = restauranteRepository.save(restaurante);
        log.info("Restaurante inserido: {} - {}", restaurante.getId(), restaurante.getNome());

        restaurante.setNome("Al Nur Kataton");
        restaurante = restauranteRepository.save(restaurante);

        log.info("Restaurante atualizado: {} - {}", restaurante.getId(), restaurante.getNome());

        restauranteRepository.delete(restaurante);
    }

}
