package br.oo.ex6;

public final class ItemCarrinho {
    private final ProdutoImutavel produto;
    private final int quantidade;

    public ItemCarrinho(ProdutoImutavel produto, int quantidade) {
        if (produto == null) throw new IllegalArgumentException("br.oo.ex1.Produto não pode ser nulo.");
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser > 0.");
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ProdutoImutavel getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Dinheiro getSubtotal() {
        return produto.getPreco().multiplicar(quantidade);
    }

    @Override
    public String toString() {
        return produto.getNome() + " x" + quantidade + " = " + getSubtotal();
    }
}