package br.com.ajvideira.algafood.domain.service;

import org.springframework.stereotype.Service;

import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.model.Cozinha;
import br.com.ajvideira.algafood.domain.model.Restaurante;
import br.com.ajvideira.algafood.domain.repository.CozinhaRepository;
import br.com.ajvideira.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

    private RestauranteRepository restauranteRepository;

    private CozinhaRepository cozinhaRepository;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
    }

    public Restaurante save(Restaurante restaurante) {
        Cozinha cozinha = cozinhaRepository.findById(restaurante.getCozinha().getId());

        if (cozinha == null) {
            throw new EntityNotFoundException(
                    String.format("Cozinha de ID #%d não existe.", restaurante.getCozinha().getId()));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

}
