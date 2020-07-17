package br.com.codenation.exceptions;

public class CapitaoNaoInformadoException extends RuntimeException{

    public CapitaoNaoInformadoException () {
        super("Captão não foi informado");
    }

    public CapitaoNaoInformadoException (String erroMessage) {
        super(erroMessage);
    }


}
