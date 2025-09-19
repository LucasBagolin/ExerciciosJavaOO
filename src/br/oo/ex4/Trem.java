package br.oo.ex4;

public class Trem implements IMeioTransporte {
    private int velocidade = 0;
    private static final int VELOCIDADE_MAX = 300;

    @Override
    public void acelerar() {
        if (velocidade + 50 > VELOCIDADE_MAX) {
            throw new IllegalStateException("O trem não pode ultrapassar " + VELOCIDADE_MAX + " km/h");
        }
        velocidade += 50;
    }

    @Override
    public void frear() {
        if (velocidade - 50 < 0) {
            throw new IllegalStateException("O trem já está parado!");
        }
        velocidade -= 50;
    }

    @Override
    public int getVelocidade() {
        return velocidade;
    }

    @Override
    public String toString() {
        return "br.oo.ex4.Trem - Velocidade atual: " + velocidade + " km/h";
    }
}