package br.com.ajvideira.algafood.domain.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.ajvideira.algafood.domain.exception.EntityInUseException;
import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.model.Cidade;
import br.com.ajvideira.algafood.domain.model.Estado;
import br.com.ajvideira.algafood.domain.repository.CidadeRepository;
import br.com.ajvideira.algafood.domain.repository.EstadoRepository;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;

    private EstadoRepository estadoRepository;

    public CidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    public Cidade save(Cidade cidade) {
        Estado estado = estadoRepository.findById(cidade.getEstado().getId()).orElse(null);

        if (estado == null) {
            throw new EntityNotFoundException(
                    String.format("Estado de ID #%d não existe.", cidade.getEstado().getId()));
        }

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void delete(Long cidadeId) {
        try {
            this.cidadeRepository.delete(cidadeId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Cidade de ID #%d não pode ser removida, pois está em uso.", cidadeId));
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Cidade de ID #%d não pode ser removida, pois não existe.", cidadeId));
        }
    }

}
