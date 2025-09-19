package br.oo.ex8;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Pac implements CalculadoraFrete {
    @Override
    public BigDecimal calcular(Pedido p) {
        BigDecimal base = new BigDecimal("14.90");
        BigDecimal porPeso = p.getPesoKg().multiply(new BigDecimal("8.00"));
        BigDecimal distancia = p.mesmaCidadeAproximada() ? new BigDecimal("3.00") : new BigDecimal("18.00");
        return base.add(porPeso).add(distancia).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() { return "br.oo.ex8.Pac"; }
}