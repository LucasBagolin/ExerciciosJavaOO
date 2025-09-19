# ~Exercícios Java OO

Lista de exercícios de **Java Orientado a Objetos**, organizados em pacotes (`br.oo.ex01` … `br.oo.ex08`).  
Cada exercício aborda um pilar ou recurso da programação orientada a objetos.

## ~Tecnologias
- Java 21
- IntelliJ IDEA
- Git + GitHub

## ~Exercícios

1. **Encapsulamento (Classe Produto)**
    - `Produto` com atributos privados e validações.
    - `MainEx01` demonstra criação e manipulação com exceções.

2. **Encapsulamento + Regra de Negócio (Desconto)**
    - `Produto` com método `aplicarDesconto(double porcentagem)`.
    - Restrições de 0% a 50%.

3. **Herança (Hierarquia de Funcionários)**
    - Classe base `Funcionario` + subclasses `Gerente` e `Desenvolvedor`.
    - Cálculo de bônus polimórfico.

4. **Polimorfismo com Interface (IMeioTransporte)**
    - Interface `IMeioTransporte` (`acelerar`, `frear`).
    - Implementações: `Carro`, `Bicicleta`, `Trem`.

5. **Abstração (Sistema de Pagamentos)**
    - Classe abstrata `FormaPagamento` (`validarPagamento`, `processarPagamento`).
    - Subclasses: `CartaoCredito`, `Boleto`, `Pix`.

6. **Imutabilidade e Objetos de Valor (Carrinho de Compras)**
    - `Dinheiro` (objeto de valor imutável).
    - `ProdutoImutavel`, `ItemCarrinho` e `Carrinho` imutável.
    - Operações retornam **novos carrinhos**.

7. **Generics (Repositório em Memória)**
    - Interface `IRepository<T, ID>` e `InMemoryRepository`.
    - Exemplo com `ProdutoEntidade` e `FuncionarioEntidade`.

8. **Padrão Strategy (Cálculo de Frete com Lambdas)**
    - Estratégias de frete: `Sedex`, `Pac`, `RetiradaNaLoja`.
    - Suporte a **lambdas** para estratégia promocional.

---

## ~Como rodar

1. Clone o repositório:
   ```bash
   git clone https://github.com/LucasBagolin/ExerciciosJavaOO.git
   cd ExerciciosJavaOO
   ```
2. Abra o IntelliJ IDEA e configure a JDK 21.
   
3. Execute a classe Main.

    ```bash
    src/
    └─ br/
       └─ oo/
          ├─ ex01/   # Encapsulamento
          ├─ ex02/   # Desconto
          ├─ ex03/   # Herança
          ├─ ex04/   # Interface
          ├─ ex05/   # Abstração
          ├─ ex06/   # Imutabilidade
          ├─ ex07/   # Generics
          └─ ex08/   # Strategy
    ```
## ~Autor
[Lucas Vieira Bagolin](https://www.linkedin.com/in/lucasbagolin/ "LinkedIn")
 — estudante de Ciência da Computação (UFRGS).