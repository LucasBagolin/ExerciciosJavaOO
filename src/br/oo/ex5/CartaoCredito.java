package br.oo.ex5;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Objects;

public class CartaoCredito extends FormaPagamento {
    private final String numero;
    private final String nomeTitular;
    private final String cvv;
    private final YearMonth validade;

    public CartaoCredito(String numero, String nomeTitular, String cvv, YearMonth validade) {
        this.numero = numero != null ? numero.replaceAll("\\s+", "") : null;
        this.nomeTitular = nomeTitular;
        this.cvv = cvv;
        this.validade = validade;
    }

    @Override
    public void validarPagamento() {
        if (nomeTitular == null || nomeTitular.trim().isEmpty()) {
            throw new PagamentoInvalidoException("Nome do titular inválido.");
        }
        if (numero == null || !numero.matches("\\d{13,19}")) {
            throw new PagamentoInvalidoException("Número de cartão inválido (13–19 dígitos).");
        }
        if (!validaLuhn(numero)) {
            throw new PagamentoInvalidoException("Número de cartão reprovado no Luhn.");
        }
        if (cvv == null || !cvv.matches("\\d{3,4}")) {
            throw new PagamentoInvalidoException("CVV inválido (3–4 dígitos).");
        }
        if (validade == null || validade.isBefore(YearMonth.now())) {
            throw new PagamentoInvalidoException("Cartão vencido.");
        }
    }

    @Override
    public void processarPagamento(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PagamentoInvalidoException("Valor deve ser positivo.");
        }
        validarPagamento();
        System.out.println("[Cartão] Autorizado: R$ " + valor);
    }

    private boolean validaLuhn(String digits) {
        int sum = 0;
        boolean alternate = false;
        for (int i = digits.length() - 1; i >= 0; i--) {
            int n = digits.charAt(i) - '0';
            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            sum += n;
            alternate = !alternate;
        }
        return sum % 10 == 0;
    }

    @Override
    public String toString() {
        String last4 = (numero != null && numero.length() >= 4) ? numero.substring(numero.length() - 4) : "????";
        return "br.oo.ex5.CartaoCredito{**** **** **** " + last4 + ", titular='" + nomeTitular + "', validade=" + validade + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, nomeTitular, cvv, validade);
    }
}
