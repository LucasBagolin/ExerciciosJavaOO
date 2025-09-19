package br.oo.ex8;

import java.math.BigDecimal;

public class RetiradaNaLoja implements CalculadoraFrete {
    @Override
    public BigDecimal calcular(Pedido p) {
        if (!p.mesmaCidadeAproximada()) {
            throw new IllegalStateException("Retirada na loja indisponível: CEP de destino não é da mesma cidade.");
        }
        return BigDecimal.ZERO;
    }

    @Override
    public String toString() { return "br.oo.ex8.RetiradaNaLoja"; }
}