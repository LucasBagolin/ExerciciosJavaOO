package br.oo.ex6;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public final class Carrinho {
    private final List<ItemCarrinho> itens;
    private final Moeda moeda;
    private final BigDecimal cupomPercentual; // null = sem cupom

    private Carrinho(List<ItemCarrinho> itens, Moeda moeda, BigDecimal cupomPercentual) {
        if (moeda == null) throw new IllegalArgumentException("br.oo.ex6.Moeda do carrinho não pode ser nula.");
        this.itens = Collections.unmodifiableList(new ArrayList<>(itens));
        this.moeda = moeda;
        if (cupomPercentual != null) validarCupom(cupomPercentual);
        this.cupomPercentual = (cupomPercentual == null) ? null : cupomPercentual.setScale(2, RoundingMode.HALF_EVEN);
        validarMoedasDosItens();
    }

    public static Carrinho vazio(Moeda moeda) {
        return new Carrinho(List.of(), moeda, null);
    }

    private void validarMoedasDosItens() {
        for (ItemCarrinho it : itens) {
            if (it.getProduto().getPreco().getMoeda() != this.moeda) {
                throw new IllegalArgumentException("Todas as moedas dos itens devem ser " + this.moeda);
            }
        }
    }

    private void validarCupom(BigDecimal percentual) {
        if (percentual.compareTo(BigDecimal.ZERO) < 0 || percentual.compareTo(new BigDecimal("30")) > 0) {
            throw new IllegalArgumentException("Cupom acima do limite: permitido de 0% a 30%.");
        }
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public Dinheiro getTotalBruto() {
        Dinheiro total = new Dinheiro(BigDecimal.ZERO, moeda);
        for (ItemCarrinho it : itens) {
            total = total.somar(it.getSubtotal());
        }
        return total;
    }

    public Dinheiro getTotalComCupom() {
        Dinheiro bruto = getTotalBruto();
        if (cupomPercentual == null) return bruto;
        return bruto.aplicarDescontoPercentual(cupomPercentual);
    }

    public Carrinho adicionarItem(ProdutoImutavel produto, int quantidade) {
        if (produto == null) throw new IllegalArgumentException("br.oo.ex1.Produto não pode ser nulo.");
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser > 0.");
        if (produto.getPreco().getMoeda() != this.moeda) {
            throw new IllegalArgumentException("br.oo.ex6.Moeda do produto deve ser " + this.moeda);
        }

        List<ItemCarrinho> novaLista = new ArrayList<>(this.itens);
        boolean mesclado = false;

        for (int i = 0; i < novaLista.size(); i++) {
            ItemCarrinho it = novaLista.get(i);
            if (it.getProduto().getNome().equalsIgnoreCase(produto.getNome())) {
                ItemCarrinho novo = new ItemCarrinho(produto, it.getQuantidade() + quantidade);
                novaLista.set(i, novo);
                mesclado = true;
                break;
            }
        }

        if (!mesclado) {
            novaLista.add(new ItemCarrinho(produto, quantidade));
        }

        return new Carrinho(novaLista, this.moeda, this.cupomPercentual);
    }

    public Carrinho removerProdutoPorNome(String nomeProduto) {
        List<ItemCarrinho> novaLista = this.itens.stream()
                .filter(it -> !it.getProduto().getNome().equalsIgnoreCase(nomeProduto))
                .collect(Collectors.toList());
        return new Carrinho(novaLista, this.moeda, this.cupomPercentual);
    }

    public Carrinho aplicarCupom(BigDecimal percentual) {
        validarCupom(percentual);
        return new Carrinho(this.itens, this.moeda, percentual.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Override
    public String toString() {
        String itensStr = itens.isEmpty() ? "(vazio)" :
                itens.stream().map(ItemCarrinho::toString).collect(Collectors.joining(" | "));
        return "br.oo.ex6.Carrinho{" +
                "itens=" + itensStr +
                ", totalBruto=" + getTotalBruto() +
                (cupomPercentual != null ? (", cupom=" + cupomPercentual + "%, totalComCupom=" + getTotalComCupom()) : "") +
                '}';
    }
}