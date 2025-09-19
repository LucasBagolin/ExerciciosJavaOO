package br.oo.ex8;

public class CepInvalidoException extends IllegalArgumentException {
    public CepInvalidoException(String message) {
        super(message);
    }
}