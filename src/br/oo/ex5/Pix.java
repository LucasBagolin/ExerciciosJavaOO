package br.oo.ex5;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Pix extends FormaPagamento {
    private final String chave;

    public Pix(String chave) {
        this.chave = chave != null ? chave.trim() : null;
    }

    @Override
    public void validarPagamento() {
        if (chave == null || chave.isEmpty()) {
            throw new PagamentoInvalidoException("Chave PIX não pode ser vazia.");
        }
        if (!(isEmail(chave) || isTelefone(chave) || isCPF(chave) || isCNPJ(chave) || isUUID(chave))) {
            throw new PagamentoInvalidoException("Chave PIX inválida (email, telefone, CPF, CNPJ ou UUID).");
        }
    }

    @Override
    public void processarPagamento(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PagamentoInvalidoException("Valor deve ser positivo.");
        }
        validarPagamento();
        System.out.println("[PIX] Transferido R$ " + valor + " para chave: " + chave);
    }

    private boolean isEmail(String s) {
        return s.contains("@") && s.indexOf('@') > 0 && s.indexOf('@') < s.length() - 1;
    }
    private boolean isTelefone(String s) {
        return s.matches("\\+?\\d{10,14}");
    }
    private boolean isCPF(String s) {
        return s.matches("\\d{11}");
    }
    private boolean isCNPJ(String s) {
        return s.matches("\\d{14}");
    }
    private boolean isUUID(String s) {
        return Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$")
                .matcher(s).matches();
    }

    @Override
    public String toString() {
        return "br.oo.ex5.Pix{" + chave + "}";
    }
}