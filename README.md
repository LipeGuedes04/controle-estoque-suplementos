# 📦 Sistema Automatizado de Controle de Estoque - StockMaster POO

## 🎯 Escopo do Sistema

O sistema simula um ecossistema digital para gerenciamento, auditoria e movimentação de inventário em almoxarifados ou lojas de suplementos. A aplicação implementa o controle dinâmico de mercadorias em memória utilizando **Coleções Dinâmicas de Objetos**, permitindo o cadastro de produtos com **ID numérico exclusivo, nome e preço de custo**. Através de barramentos de busca por identificador único (ID), o operador executa **atualizações incrementais de entrada de mercadorias**, processa **fluxos de venda com baixa automática** e valida travas de segurança contra estoque insuficiente para evitar inconsistências fiscais no saldo.

### 🔄 Linha de Processamento
```text
[ Cadastro de Item ] ➔ [ Injeção na Lista ] ➔ [ Entrada de Lote (ID) ] ➔ [ Fluxo de Venda (ID) ] ➔ [ Validação de Saldo/Baixa ]
```

---

## 🎨 Arquitetura de Software (Interação entre Camadas)

O design do ecossistema adota o desacoplamento de código dividindo estritamente as responsabilidades entre a classe controladora (interface) e a classe de entidade (regras de negócio), integradas por meio da interface `List`. Veja o mapa do fluxo de dados:

```text
========================================================================
  CAMADA DE INTERFACE (Main.java)
========================================================================
     [ Scanner ] ── input ──► [ Menu Loop (while) ]
                                    │
                         direciona via switch-case
                                    │
       ┌───────────────┬────────────┴────────────┬───────────────┐
       ▼               ▼                         ▼               ▼
   [Case 1]        [Case 2]                  [Case 3]        [Case 4]
  Instancia       Percorre e                Busca por ID    Busca por ID
  novo Produto   Lista a Frota             p/ Entrada de   p/ Registro de
       │               │                      Estoque          Venda
  produtos.add()  Loop for-each                  │               │
       │               │                         ├───────┬───────┘
       ▼               ▼                         ▼       ▼
=======================╪===================== [ if (c.getId() == buscarID) ]
  CAMADA DE DOMÍNIO (  │                        encontra Objeto na lista
=======================╪=================================╪==============
                       ▼                                 ▼
               [ Produto 1, 2... N ] ◄──────────── [ Objeto Alvo ]
               Invocação toString()               Acessa métodos internos
                                                  (.adicionarEstoque() / .venderProduto())
```

### 📋 Inventário de Componentes

| Componente Técnico | Atuação e Responsabilidade |
| :--- | :--- |
| 🚀 `Main.java` | Camada de controle e interface de console. Gerencia o laço do menu principal, captura as entradas do teclado e executa os algoritmos de busca linear por ID. |
| 🧪 `Produto.java` | Camada de domínio (Entidade). Define a estrutura do objeto, protege seus atributos e encapsula os métodos matemáticos de mutação de saldo de estoque. |
| 📦 `List<Produto>` | Coleção elástica (`ArrayList`) que atua como banco de dados temporário em memória RAM, indexando os produtos instanciados. |

---

## 🧠 Tópicos Práticos Praticados

* **Coleções Homogêneas de Objetos (`List<Produto>`):** Manipulação de listas dinâmicas para agrupar instâncias de classes customizadas, permitindo crescimento elástico do banco de dados em memória.
* **Encapsulamento e Métodos Mutadores:** Proteção dos estados do objeto através de atributos privados, onde alterações críticas (como entrada e saída de estoque) só ocorrem por métodos específicos (`adicionarEstoque` e `venderProduto`).
* **Algoritmo de Busca Linear com Flag de Referência:** Implementação de varredura sequencial em listas para localizar um objeto por seu atributo identificador e isolar sua referência de memória para operações posteriores.

---

## 💻 Demonstração Operacional (Terminal)

Veja uma simulação do comportamento do console ao gerenciar o estoque, dar entrada e realizar uma venda:

```text
________ Estoque de Suplementos ___________

===== Menu Principal =======
1 - Cadastrar produto
2 - Verificar estoque
3 - Dar entrada no estoque
4 - Vender produto
5 - Sair
Escolha uma opção: 1
Insira o id do produto: 101
Insira o nome do produto: Whey Protein Isolated
Insira o preco do produto: 149.90
Produto cadastrado com sucesso!!

===== Menu Principal =======
1 - Cadastrar produto
2 - Verificar estoque
3 - Dar entrada no estoque
4 - Vender produto
5 - Sair
Escolha uma opção: 3
Digite o Id do produto que chegou: 101
Digite a quantidade que será inserida no estoque: 50

===== Menu Principal =======
1 - Cadastrar produto
2 - Verificar estoque
3 - Dar entrada no estoque
4 - Vender produto
5 - Sair
Escolha uma opção: 2
ID: 101| Produto: Whey Protein Isolated | Preço: 149.9| Estoque: 50

===== Menu Principal =======
1 - Cadastrar produto
2 - Verificar estoque
3 - Dar entrada no estoque
4 - Vender produto
5 - Sair
Escolha uma opção: 4
Digite o Id do produto que será vendido: 101
Digite a quantidade que será vendida: 10
Venda realizada ....

===== Menu Principal =======
1 - Cadastrar produto
2 - Verificar estoque
3 - Dar entrada no estoque
4 - Vender produto
5 - Sair
Escolha uma opção: 5
Programa Finalizado!! 
```

