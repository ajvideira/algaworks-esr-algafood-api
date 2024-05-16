package br.com.ajvideira.algafood.api.di.model;

import lombok.Getter;

@Getter
public class Cliente {

    private String nome;
    private String email;
    private String telefone;
    private boolean ativo;

    public Cliente(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public void ativar() {
        this.ativo = true;
    }

}
