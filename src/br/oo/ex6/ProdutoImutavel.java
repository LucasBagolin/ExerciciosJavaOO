package br.oo.ex6;

import java.util.Objects;

public final class ProdutoImutavel {
    private final String nome;
    private final Dinheiro preco;

    public ProdutoImutavel(String nome, Dinheiro preco) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        if (preco == null) throw new IllegalArgumentException("Preço não pode ser nulo.");
        this.nome = nome.trim();
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public Dinheiro getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "br.oo.ex1.Produto{" + nome + ", " + preco + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoImutavel)) return false;
        ProdutoImutavel produto = (ProdutoImutavel) o;
        return nome.equalsIgnoreCase(produto.nome) && preco.equals(produto.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase(), preco);
    }
}