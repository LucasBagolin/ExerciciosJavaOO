package br.oo.ex5;

public class PagamentoInvalidoException extends IllegalArgumentException {
    public PagamentoInvalidoException(String message) {
        super(message);
    }
}