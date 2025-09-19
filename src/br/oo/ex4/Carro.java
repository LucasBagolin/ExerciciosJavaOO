package br.oo.ex4;

public class Carro implements IMeioTransporte {
    private int velocidade = 0;
    private static final int VELOCIDADE_MAX = 200;

    @Override
    public void acelerar() {
        if (velocidade + 20 > VELOCIDADE_MAX) {
            throw new IllegalStateException("O carro não pode ultrapassar " + VELOCIDADE_MAX + " km/h");
        }
        velocidade += 20;
    }

    @Override
    public void frear() {
        if (velocidade - 20 < 0) {
            throw new IllegalStateException("O carro já está parado!");
        }
        velocidade -= 20;
    }

    @Override
    public int getVelocidade() {
        return velocidade;
    }

    @Override
    public String toString() {
        return "br.oo.ex4.Carro - Velocidade atual: " + velocidade + " km/h";
    }
}