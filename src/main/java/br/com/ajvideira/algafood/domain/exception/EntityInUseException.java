package br.com.ajvideira.algafood.domain.exception;

public class EntityInUseException extends RuntimeException {

    public EntityInUseException(String message) {
        super(message);
    }

}
