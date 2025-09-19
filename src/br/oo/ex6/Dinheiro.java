package br.oo.ex6;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Dinheiro {
    private final BigDecimal valor;
    private final Moeda moeda;

    public Dinheiro(BigDecimal valor, Moeda moeda) {
        if (moeda == null) throw new IllegalArgumentException("br.oo.ex6.Moeda não pode ser nula.");
        if (valor == null) throw new IllegalArgumentException("Valor não pode ser nulo.");
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo.");
        }
        this.valor = valor.setScale(2, RoundingMode.HALF_EVEN);
        this.moeda = moeda;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Moeda getMoeda() {
        return moeda;
    }

    public Dinheiro somar(Dinheiro outro) {
        validarMesmaMoeda(outro);
        return new Dinheiro(this.valor.add(outro.valor), this.moeda);
    }

    public Dinheiro subtrair(Dinheiro outro) {
        validarMesmaMoeda(outro);
        BigDecimal res = this.valor.subtract(outro.valor);
        if (res.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Resultado monetário não pode ser negativo.");
        }
        return new Dinheiro(res, this.moeda);
    }

    public Dinheiro multiplicar(int fator) {
        if (fator < 0) throw new IllegalArgumentException("Fator não pode ser negativo.");
        return new Dinheiro(this.valor.multiply(BigDecimal.valueOf(fator)), this.moeda);
    }

    public Dinheiro aplicarDescontoPercentual(BigDecimal percentual) {
        if (percentual == null) throw new IllegalArgumentException("Percentual não pode ser nulo.");
        if (percentual.compareTo(BigDecimal.ZERO) < 0 || percentual.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("Percentual deve estar entre 0 e 100.");
        }
        BigDecimal fator = BigDecimal.ONE.subtract(percentual.divide(new BigDecimal("100"), 10, RoundingMode.HALF_EVEN));
        return new Dinheiro(this.valor.multiply(fator).setScale(2, RoundingMode.HALF_EVEN), this.moeda);
    }

    private void validarMesmaMoeda(Dinheiro outro) {
        if (outro == null) throw new IllegalArgumentException("br.oo.ex6.Dinheiro de comparação não pode ser nulo.");
        if (this.moeda != outro.moeda) {
            throw new IllegalArgumentException("Moedas diferentes: " + this.moeda + " vs " + outro.moeda);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dinheiro)) return false;
        Dinheiro dinheiro = (Dinheiro) o;
        return valor.compareTo(dinheiro.valor) == 0 && moeda == dinheiro.moeda;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, moeda);
    }

    @Override
    public String toString() {
        return moeda + " " + valor.toPlainString();
    }
}