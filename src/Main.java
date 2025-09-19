import br.oo.ex1.*;
import br.oo.ex3.*;
import br.oo.ex4.*;
import br.oo.ex5.*;
import br.oo.ex6.*;
import br.oo.ex7.*;
import br.oo.ex8.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.YearMonth;
import java.util.Optional;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        System.out.println("Exercício 1:");
        Produto p1 = new Produto("Notebook", 3500.0, 10);
        System.out.println("br.oo.ex1.Produto criado: " + p1);
        p1.setPreco(3200.0);
        p1.setQuantidadeEmEstoque(15);
        System.out.println("br.oo.ex1.Produto atualizado: " + p1);

        try {
            p1.setPreco(-100.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao atualizar preço: " + e.getMessage());
        }

        try {
            p1.setNome("");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao atualizar nome: " + e.getMessage());
        }

        try {
            Produto p2 = new Produto("Mouse", 50.0, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar produto: " + e.getMessage());
        }

        System.out.println("\nExercício 2:");
        Produto p2 = new Produto("Fone de Ouvido", 200.00, 5);
        System.out.println("Inicial: " + p2);

        p2.aplicarDesconto(10); // 10% -> 180.00
        System.out.println("Após 10%: " + p2);

        p2.aplicarDesconto(50); // 50% -> 90.00
        System.out.println("Após 50%: " + p2);

        try {
            p2.aplicarDesconto(-5); // inválido
        } catch (IllegalArgumentException e) {
            System.out.println("Erro (desconto negativo): " + e.getMessage());
        }

        try {
            p2.aplicarDesconto(60); // inválido
        } catch (IllegalArgumentException e) {
            System.out.println("Erro (acima do limite): " + e.getMessage());
        }

        System.out.println("\nExercício 3:");
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Gerente("Luana", new BigDecimal("10000")));
        funcionarios.add(new Desenvolvedor("Pedro", new BigDecimal("6000")));
        funcionarios.add(new Desenvolvedor("Maria", new BigDecimal("8000")));

        for (Funcionario f : funcionarios) {
            System.out.println(f.getNome() + " - Salário: " + f.getSalario() +
                    " | Bônus: " + f.calcularBonus());
        }

        System.out.println("\nExercício 4:");
        List<IMeioTransporte> meios = new ArrayList<>();
        meios.add(new Carro());
        meios.add(new Bicicleta());
        meios.add(new Trem());

        for (IMeioTransporte m : meios) {
            try {
                m.acelerar();
                m.acelerar();
                System.out.println(m);
                m.frear();
                System.out.println("Após frear: " + m);
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        System.out.println("\nExercício 5:");
        FormaPagamento cartaoOk = new CartaoCredito(
                "4539 1488 0343 6467",
                "Lucas Bagolin",
                "123",
                YearMonth.now().plusMonths(6)
        );

        FormaPagamento boletoOk = new Boleto(
                "23793381286007700005375000012345670000012345"
        );

        FormaPagamento pixOk = new Pix("lucas@example.com");

        FormaPagamento cartaoVencido = new CartaoCredito(
                "4539148803436467",
                "Titular Vencido",
                "999",
                YearMonth.now().minusMonths(1)
        );
        FormaPagamento boletoCurto = new Boleto("123456"); // inválido
        FormaPagamento pixInvalido = new Pix("chave-ruim");

        List<FormaPagamento> formas = List.of(cartaoOk, boletoOk, pixOk, cartaoVencido, boletoCurto, pixInvalido);

        for (FormaPagamento f : formas) {
            try {
                System.out.println("Validando: " + f);
                f.validarPagamento();
                f.processarPagamento(new BigDecimal("149.90"));
                System.out.println("Sucesso");
            } catch (PagamentoInvalidoException e) {
                System.out.println("Falha de validação: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }

        System.out.println("\nExercício 6:");
        Moeda moeda = Moeda.BRL;

        ProdutoImutavel notebook = new ProdutoImutavel("Notebook", new Dinheiro(new BigDecimal("3500.00"), moeda));
        ProdutoImutavel mouse    = new ProdutoImutavel("Mouse",    new Dinheiro(new BigDecimal("80.00"),   moeda));
        ProdutoImutavel teclado  = new ProdutoImutavel("Teclado",  new Dinheiro(new BigDecimal("150.00"),  moeda));

        Carrinho c0 = Carrinho.vazio(moeda);
        System.out.println("c0: " + c0);

        Carrinho c1 = c0.adicionarItem(notebook, 1);
        System.out.println("c1: " + c1);

        Carrinho c2 = c1.adicionarItem(mouse, 2).adicionarItem(teclado, 1);
        System.out.println("c2: " + c2);

        Carrinho c3 = c2.aplicarCupom(new BigDecimal("20"));
        System.out.println("c3 (20%): " + c3);

        Carrinho c4 = c3.removerProdutoPorNome("Mouse");
        System.out.println("c4 (sem mouse): " + c4);

        try {
            c4.aplicarCupom(new BigDecimal("35"));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro cupom: " + e.getMessage());
        }

        try {
            c4.adicionarItem(teclado, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro quantidade: " + e.getMessage());
        }

        System.out.println("\nExercício 7:");
        IRepository<ProdutoEntidade, UUID> repoProdutos = new InMemoryRepository<>();
        IRepository<FuncionarioEntidade, UUID> repoFuncionarios = new InMemoryRepository<>();

        ProdutoEntidade p3 = new ProdutoEntidade(UUID.randomUUID(), "Notebook", new BigDecimal("3500.00"));
        ProdutoEntidade p4 = new ProdutoEntidade(UUID.randomUUID(), "Mouse",    new BigDecimal("80.00"));
        FuncionarioEntidade f1 = new FuncionarioEntidade(UUID.randomUUID(), "Alice", new BigDecimal("10000.00"));
        FuncionarioEntidade f2 = new FuncionarioEntidade(UUID.randomUUID(), "Matheus",   new BigDecimal("6000.00"));

        repoProdutos.salvar(p3);
        repoProdutos.salvar(p4);
        repoFuncionarios.salvar(f1);
        repoFuncionarios.salvar(f2);

        System.out.println("Produtos:");
        List<ProdutoEntidade> listaProdutos = repoProdutos.listarTodos();
        listaProdutos.forEach(System.out::println);

        System.out.println("Funcionários:");
        repoFuncionarios.listarTodos().forEach(System.out::println);

        System.out.println("Buscando produto p3:");
        Optional<ProdutoEntidade> achado = repoProdutos.buscarPorId(p3.getId());
        System.out.println(achado.orElse(null));

        p3.setPreco(new BigDecimal("3299.90"));
        repoProdutos.salvar(p3);
        System.out.println("Após atualização de preço do p3:");
        repoProdutos.listarTodos().forEach(System.out::println);

        System.out.println("Removendo p4...");
        repoProdutos.remover(p4.getId());
        repoProdutos.listarTodos().forEach(System.out::println);

        try {
            System.out.println("Removendo ID inexistente...");
            repoProdutos.remover(UUID.randomUUID());
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        System.out.println("Tentando alterar a lista retornada por listarTodos():");
        List<ProdutoEntidade> copiaImutavel = repoProdutos.listarTodos();
        try {
            copiaImutavel.add(p4);
        } catch (UnsupportedOperationException e) {
            System.out.println("Lista é imutável (OK).\n");
        }

        System.out.println("Exercício 8:");
        String cepLoja = "90010-000";
        String cepCliente = "01310-000";

        Pedido pedido = new Pedido(
                cepLoja,
                cepCliente,
                new BigDecimal("2.5"),
                new BigDecimal("450.00"),
                new Pac()
        );

        System.out.println("Estratégia atual (PAC): R$ " + pedido.calcularFrete());

        pedido.setEstrategiaFrete(new Sedex());
        System.out.println("Estratégia atual (SEDEX): R$ " + pedido.calcularFrete());

        try {
            pedido.setEstrategiaFrete(new RetiradaNaLoja());
            System.out.println("Estratégia atual (Retirada): R$ " + pedido.calcularFrete());
        } catch (Exception e) {
            System.out.println("Retirada indisponível: " + e.getMessage());
        }

        BigDecimal limiarFreteGratis = new BigDecimal("300.00");
        CalculadoraFrete fretePromocional = (p) -> {
            if (p.getValorItens().compareTo(limiarFreteGratis) >= 0) {
                return BigDecimal.ZERO;
            } else {
                return new Sedex().calcular(p);
            }
        };

        pedido.setEstrategiaFrete(fretePromocional);
        System.out.println("Estratégia atual (Promo λ): R$ " + pedido.calcularFrete());

        try {
            Pedido erroCep = new Pedido("90010000", "abcde-123", new BigDecimal("1.0"), new BigDecimal("50"), new Pac());
            System.out.println(erroCep.calcularFrete());
        } catch (CepInvalidoException e) {
            System.out.println("CEP inválido detectado: " + e.getMessage());
        }
    }
}