package br.oo.ex8;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sedex implements CalculadoraFrete {
    @Override
    public BigDecimal calcular(Pedido p) {
        BigDecimal base = new BigDecimal("19.90");
        BigDecimal porPeso = p.getPesoKg().multiply(new BigDecimal("12.00"));
        BigDecimal distancia = p.mesmaCidadeAproximada() ? new BigDecimal("5.00") : new BigDecimal("25.00");
        return base.add(porPeso).add(distancia).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() { return "br.oo.ex8.Sedex"; }
}