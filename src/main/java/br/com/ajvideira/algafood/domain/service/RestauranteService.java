package br.com.ajvideira.algafood.domain.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.ajvideira.algafood.domain.exception.EntityInUseException;
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
        Cozinha cozinha = cozinhaRepository.findById(restaurante.getCozinha().getId()).orElse(null);

        if (cozinha == null) {
            throw new EntityNotFoundException(
                    String.format("Restaurante de ID #%d não existe.", restaurante.getCozinha().getId()));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public void delete(Long restauranteId) {
        try {
            restauranteRepository.findById(restauranteId).ifPresentOrElse(
                    restaurante -> restauranteRepository.delete(
                            restaurante),
                    () -> {
                        throw new EmptyResultDataAccessException(1);
                    });
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Restaurante de ID #%d não pode ser removido, pois está em uso.", restauranteId));
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Restaurante de ID #%d não pode ser removido, pois não existe.", restauranteId));
        }
    }

}
