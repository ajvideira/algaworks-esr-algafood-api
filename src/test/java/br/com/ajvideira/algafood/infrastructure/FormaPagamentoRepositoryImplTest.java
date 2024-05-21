package br.com.ajvideira.algafood.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

import br.com.ajvideira.algafood.domain.model.FormaPagamento;
import br.com.ajvideira.algafood.domain.repository.FormaPagamentoRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
class FormaPagamentoRepositoryImplTest {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Test
    void shouldFindAll() {
        assertEquals(2, formaPagamentoRepository.findAll().size());
    }

    @Test
    void shouldFindByIdExists() {
        assertNotNull(formaPagamentoRepository.findById(1L));
    }

    @Test
    void shouldNotFindByIdNotExists() {
        assertNull(formaPagamentoRepository.findById(10L));
    }

    @Test
    void shouldAdd() {
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setDescricao("PIX");

        formaPagamento = formaPagamentoRepository.save(formaPagamento);

        assertNotNull(formaPagamento.getId());
    }

    @Test
    void shouldUpdateManagedEntity() {
        FormaPagamento formaPagamento = formaPagamentoRepository.findById(1L);
        formaPagamento.setDescricao("TED");

        formaPagamento = formaPagamentoRepository.save(formaPagamento);

        assertEquals(formaPagamentoRepository.findById(formaPagamento.getId()), formaPagamento);
    }

    @Test
    void shouldUpdateNonManagedEntity() {
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setId(1L);
        formaPagamento.setDescricao("TED");

        formaPagamento = formaPagamentoRepository.save(formaPagamento);

        assertEquals(formaPagamentoRepository.findById(formaPagamento.getId()), formaPagamento);
    }

    @Test
    void shouldDeleteManagedEntity() {
        FormaPagamento formaPagamento = formaPagamentoRepository.findById(1L);
        formaPagamentoRepository.delete(formaPagamento);

        assertNull(formaPagamentoRepository.findById(1L));
    }

    @Test
    void shouldDeleteNonManagedEntity() {
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setId(2L);
        formaPagamentoRepository.delete(formaPagamento);

        assertNull(formaPagamentoRepository.findById(2L));
    }

}
