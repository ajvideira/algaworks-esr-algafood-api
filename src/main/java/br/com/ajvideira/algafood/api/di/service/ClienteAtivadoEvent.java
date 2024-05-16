package br.com.ajvideira.algafood.api.di.service;

import br.com.ajvideira.algafood.api.di.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClienteAtivadoEvent {

    private Cliente cliente;

}
