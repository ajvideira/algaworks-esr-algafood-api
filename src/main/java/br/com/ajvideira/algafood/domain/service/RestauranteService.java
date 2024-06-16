package br.com.ajvideira.algafood.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.ajvideira.algafood.domain.exception.EntityInUseException;
import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.model.Cozinha;
import br.com.ajvideira.algafood.domain.model.FormaPagamento;
import br.com.ajvideira.algafood.domain.model.Restaurante;
import br.com.ajvideira.algafood.domain.repository.CidadeRepository;
import br.com.ajvideira.algafood.domain.repository.CozinhaRepository;
import br.com.ajvideira.algafood.domain.repository.FormaPagamentoRepository;
import br.com.ajvideira.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

    private RestauranteRepository restauranteRepository;

    private CozinhaRepository cozinhaRepository;

    private CidadeRepository cidadeRepository;

    private FormaPagamentoRepository formaPagamentoRepository;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository,
            CidadeRepository cidadeRepository, FormaPagamentoRepository formaPagamentoRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
        this.cidadeRepository = cidadeRepository;
        this.formaPagamentoRepository = formaPagamentoRepository;
    }

    public Restaurante save(Restaurante restaurante) {
        Cozinha cozinha = cozinhaRepository.findById(restaurante.getCozinha().getId()).orElse(null);

        if (cozinha == null) {
            throw new EntityNotFoundException(
                    String.format("Cozinha de ID #%d não existe.", restaurante.getCozinha().getId()));
        }

        restaurante.setCozinha(cozinha);

        var cidade = cidadeRepository.findById(restaurante.getEndereco().getCidade().getId()).orElse(null);

        if (cidade == null) {
            throw new EntityNotFoundException(
                    String.format("Cidade de ID #%d não existe.", restaurante.getEndereco().getCidade().getId()));
        }

        List<FormaPagamento> formasPagamento = new ArrayList<>();

        for (FormaPagamento formaPagamento : restaurante.getFormasPagamento()) {
            var novaFormaPagamento = formaPagamentoRepository.findById(formaPagamento.getId()).orElse(null);
            if (novaFormaPagamento == null) {
                throw new EntityNotFoundException(
                        String.format("Forma de pagamento de ID #%d não existe.", formaPagamento.getId()));
            }
            formasPagamento.add(novaFormaPagamento);
        }

        restaurante.setFormasPagamento(formasPagamento);

        restaurante.getEndereco().setCidade(cidade);

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
