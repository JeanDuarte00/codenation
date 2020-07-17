package br.com.codenation.exceptions;

public class IdentificadorUtilizadoException extends RuntimeException{

    public IdentificadorUtilizadoException () {
        super("Identificador já existe");
    }

    public IdentificadorUtilizadoException (String erroMessage) {
        super(erroMessage);
    }
}
