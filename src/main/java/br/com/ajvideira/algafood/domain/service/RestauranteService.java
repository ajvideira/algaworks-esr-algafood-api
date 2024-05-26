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
        Cozinha cozinha = cozinhaRepository.findById(restaurante.getCozinha().getId());

        if (cozinha == null) {
            throw new EntityNotFoundException(
                    String.format("Cozinha de ID #%d não existe.", restaurante.getCozinha().getId()));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public void delete(Long restauranteId) {
        try {
            this.restauranteRepository.delete(restauranteId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Restaurante de ID #%d não pode ser removida, pois está em uso.", restauranteId));
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Restaurante de ID #%d não pode ser removida, pois não existe.", restauranteId));
        }
    }

}
