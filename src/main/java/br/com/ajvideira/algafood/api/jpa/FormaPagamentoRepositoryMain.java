package br.com.ajvideira.algafood.api.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.ajvideira.algafood.api.AlgafoodApiApplication;
import br.com.ajvideira.algafood.api.domain.model.FormaPagamento;
import br.com.ajvideira.algafood.api.domain.repository.FormaPagamentoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FormaPagamentoRepositoryMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);

        FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);

        formaPagamentoRepository.findAll().stream().map(FormaPagamento::getDescricao)
                .forEach(descricao -> log.info(descricao));

        FormaPagamento formaPagamentoPorId = formaPagamentoRepository.findById(1L);

        log.info("Forma de pagamento encontrada: {}", formaPagamentoPorId.getDescricao());

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setDescricao("PIX");

        formaPagamento = formaPagamentoRepository.save(formaPagamento);
        log.info("Forma de pagamento inserida: {} - {}", formaPagamento.getId(), formaPagamento.getDescricao());

        formaPagamento.setDescricao("TED");
        formaPagamento = formaPagamentoRepository.save(formaPagamento);

        log.info("Forma de pagamento atualizada: {} - {}", formaPagamento.getId(), formaPagamento.getDescricao());

        formaPagamentoRepository.delete(formaPagamento);
    }

}
