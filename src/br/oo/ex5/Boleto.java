package br.oo.ex5;

import java.math.BigDecimal;

public class Boleto extends FormaPagamento {
    private final String linhaDigitavel; // 47 ou 48 dígitos

    public Boleto(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel != null ? linhaDigitavel.replaceAll("\\s+", "") : null;
    }

    @Override
    public void validarPagamento() {
        if (linhaDigitavel == null || !linhaDigitavel.matches("\\d{47,48}")) {
            throw new PagamentoInvalidoException("Linha digitável inválida (deve ter 47 ou 48 dígitos numéricos).");
        }
    }

    @Override
    public void processarPagamento(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PagamentoInvalidoException("Valor deve ser positivo.");
        }
        validarPagamento();
        System.out.println("[br.oo.ex5.Boleto] Gerado e pago: R$ " + valor + " | Linha: " + mascara());
    }

    private String mascara() {
        if (linhaDigitavel == null) return "N/A";
        return linhaDigitavel.substring(0, 5) + "." + linhaDigitavel.substring(5, 10) + " .... " +
                linhaDigitavel.substring(linhaDigitavel.length() - 6);
    }

    @Override
    public String toString() {
        return "br.oo.ex5.Boleto{" + mascara() + "}";
    }
}