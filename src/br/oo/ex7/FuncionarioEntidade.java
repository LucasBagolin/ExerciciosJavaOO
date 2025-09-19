package br.oo.ex7;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class FuncionarioEntidade implements Identificavel<UUID> {

    private final UUID id;
    private String nome;
    private BigDecimal salario;

    public FuncionarioEntidade(UUID id, String nome, BigDecimal salario) {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo.");
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome inválido.");
        if (salario == null || salario.signum() <= 0) throw new IllegalArgumentException("Salário deve ser > 0.");
        this.id = id;
        this.nome = nome.trim();
        this.salario = salario;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome inválido.");
        this.nome = nome.trim();
    }

    public void setSalario(BigDecimal salario) {
        if (salario == null || salario.signum() <= 0) throw new IllegalArgumentException("Salário deve ser > 0.");
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "br.oo.ex7.FuncionarioEntidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", salario=" + salario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FuncionarioEntidade)) return false;
        FuncionarioEntidade that = (FuncionarioEntidade) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}