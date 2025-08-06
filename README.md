## Projeto Banco Digital - CLI

Um simples simulador de terminal bancário desenvolvido em Java para aplicar e demonstrar conceitos fundamentais de Programação Orientada a Objetos.

A aplicação permite a criação de clientes, a abertura de múltiplas contas (corrente e poupança) para cada cliente, e a realização de operações bancárias essenciais através de um sistema de menus interativos.

### Funcionalidades

* Cadastro de Clientes: Permite a criação de novos clientes com nome e CPF, com validação para garantir que cada CPF seja único.

* Criação de Múltiplas Contas: Um único cliente pode possuir contas corrente e contas poupança.

* Sistema de "Login": O usuário acessa o sistema informando o CPF, e a partir daí opera dentro do contexto daquele cliente.

### Operações Bancárias:

* Depósito

* Saque (com validação de saldo)

* Transferência entre contas

* Consulta: Listagem de todas as contas pertencentes a um cliente, com diferenciação de tipo (Corrente/Poupança).

* Interface de Console Robusta: Navegação intuitiva através de menus e tratamento seguro de entradas do usuário para evitar erros e quebras no programa.

### 📂 Estrutura do Projeto

O código está organizado nos seguintes pacotes:

* dominio: Contém as classes de modelo (entidades) do sistema, como Conta, Cliente.

* servico: Contém a lógica de negócio e o gerenciamento dos dados, como na classe Banco.

* aplicacao: Responsável pela interação com o usuário (a interface de linha de comando), como na classe Terminal.

* util: Classes de utilidade reutilizáveis, como o LeitorDeConsole.

* excecao: Classes de exceções customizadas para as regras de negócio.

![Demonstração do terminal em ação](assets/banco.gif)
