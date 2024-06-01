package br.com.ajvideira.algafood.domain.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.ajvideira.algafood.domain.exception.EntityInUseException;
import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.model.Estado;
import br.com.ajvideira.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

    private EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Estado save(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void delete(Long estadoId) {
        try {
            estadoRepository.findById(estadoId).ifPresentOrElse(
                    estado -> estadoRepository.delete(estado),
                    () -> {
                        throw new EmptyResultDataAccessException(1);
                    });
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Estado de ID #%d não pode ser removido, pois está em uso.", estadoId));
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Estado de ID #%d não pode ser removido, pois não existe.", estadoId));
        }
    }

}
