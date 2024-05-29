package br.com.ajvideira.algafood.domain.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.ajvideira.algafood.domain.exception.EntityInUseException;
import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.model.Cozinha;
import br.com.ajvideira.algafood.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

    private CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public Cozinha save(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void delete(Long cozinhaId) {
        try {
            cozinhaRepository.findById(cozinhaId).ifPresentOrElse(
                    cozinha -> cozinhaRepository.delete(cozinha),
                    () -> {
                        throw new EmptyResultDataAccessException(1);
                    });
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Cozinha de ID #%d não pode ser removida, pois está em uso.", cozinhaId));
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Cozinha de ID #%d não pode ser removida, pois não existe.", cozinhaId));
        }
    }

}
