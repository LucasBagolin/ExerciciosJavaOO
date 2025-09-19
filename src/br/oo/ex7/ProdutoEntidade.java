package br.oo.ex7;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class ProdutoEntidade implements Identificavel<UUID> {

    private final UUID id;
    private String nome;
    private BigDecimal preco;

    public ProdutoEntidade(UUID id, String nome, BigDecimal preco) {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo.");
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome inválido.");
        if (preco == null || preco.signum() < 0) throw new IllegalArgumentException("Preço deve ser >= 0.");
        this.id = id;
        this.nome = nome.trim();
        this.preco = preco;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome inválido.");
        this.nome = nome.trim();
    }

    public void setPreco(BigDecimal preco) {
        if (preco == null || preco.signum() < 0) throw new IllegalArgumentException("Preço deve ser >= 0.");
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "br.oo.ex7.ProdutoEntidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoEntidade)) return false;
        ProdutoEntidade that = (ProdutoEntidade) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}