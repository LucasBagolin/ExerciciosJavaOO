package br.oo.ex8;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Pedido {
    private final String origemCep;
    private final String destinoCep;
    private final BigDecimal pesoKg;
    private final BigDecimal valorItens;
    private CalculadoraFrete estrategiaFrete;

    public Pedido(String origemCep, String destinoCep,
                  BigDecimal pesoKg, BigDecimal valorItens,
                  CalculadoraFrete estrategiaInicial) {
        this.origemCep = normalizaCep(origemCep);
        this.destinoCep = normalizaCep(destinoCep);
        validarCep(this.origemCep);
        validarCep(this.destinoCep);

        if (pesoKg == null || pesoKg.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Peso (kg) deve ser > 0.");
        }
        if (valorItens == null || valorItens.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor dos itens deve ser >= 0.");
        }
        this.pesoKg = pesoKg.setScale(2, RoundingMode.HALF_EVEN);
        this.valorItens = valorItens.setScale(2, RoundingMode.HALF_EVEN);
        this.estrategiaFrete = Objects.requireNonNull(estrategiaInicial, "Estratégia de frete não pode ser nula.");
    }

    public String getOrigemCep() { return origemCep; }
    public String getDestinoCep() { return destinoCep; }
    public BigDecimal getPesoKg() { return pesoKg; }
    public BigDecimal getValorItens() { return valorItens; }

    public void setEstrategiaFrete(CalculadoraFrete nova) {
        this.estrategiaFrete = Objects.requireNonNull(nova, "Estratégia de frete não pode ser nula.");
    }

    public BigDecimal calcularFrete() {
        validarCep(origemCep);
        validarCep(destinoCep);
        return estrategiaFrete.calcular(this).setScale(2, RoundingMode.HALF_EVEN);
    }

    public static String normalizaCep(String cep) {
        if (cep == null) return null;
        return cep.replaceAll("\\D", ""); // só dígitos
    }

    public static void validarCep(String cepSomenteDigitos) {
        if (cepSomenteDigitos == null || !cepSomenteDigitos.matches("\\d{8}")) {
            throw new CepInvalidoException("CEP inválido. Use 99999-999 ou 99999999.");
        }
    }

    public static String prefixo5(String cepSomenteDigitos) {
        return cepSomenteDigitos.substring(0, 5);
    }

    public boolean mesmaCidadeAproximada() {
        return prefixo5(origemCep).equals(prefixo5(destinoCep));
    }
}