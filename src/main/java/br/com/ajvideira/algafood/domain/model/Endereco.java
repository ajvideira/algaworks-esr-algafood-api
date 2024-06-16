package br.com.ajvideira.algafood.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Endereco {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "cidade_id")
    private Cidade cidade;

}
