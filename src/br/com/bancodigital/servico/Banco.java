package br.com.bancodigital.servico;

import br.com.bancodigital.dominio.Cliente;
import br.com.bancodigital.dominio.Conta;
import br.com.bancodigital.dominio.ContaCorrente;
import br.com.bancodigital.dominio.ContaPoupanca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Banco {
    private Map<String, Conta> contas;
    private Map<String, Cliente> clientes;
    private int proximoNumeroConta = 10000;

    public Banco() {
        this.contas = new HashMap<>();
        this.clientes = new HashMap<>();
    }

    public Cliente criarCliente(String nome, String cpf) {
        if (this.clientes.containsKey(cpf)) {
            System.out.println("Aviso: Cliente com CPF " + cpf + " já está cadastrado.");
            return null;
        } else {
            Cliente novoCliente = new Cliente(nome, cpf);
            this.clientes.put(cpf, novoCliente);
            return novoCliente;
        }
    }

    public Conta criarContaCorrente(Cliente cliente){
        Cliente titularDaConta = this.clientes.get(cliente.getCpf());
        if (titularDaConta == null) {
            System.out.println("Erro: Não é possível criar a conta. Cliente com CPF " + cliente.getCpf() + " não encontrado.");
            return null;
        }
        String agencia = "0001";
        String numero = String.valueOf(this.proximoNumeroConta++);
        Conta novaConta = new ContaCorrente(titularDaConta, agencia, numero);
        this.contas.put(numero, novaConta);
        return novaConta;
    }

    public Conta criarContaPoupanca(Cliente cliente){
        Cliente titularDaConta = this.clientes.get(cliente.getCpf());
        if (titularDaConta == null) {
            System.out.println("Erro: Não é possível criar a conta. Cliente com CPF " + cliente.getCpf() + " não encontrado.");
            return null;
        }
        String agencia = "0001";
        String numero = String.valueOf(this.proximoNumeroConta++);
        Conta novaConta = new ContaPoupanca(titularDaConta, agencia, numero);
        this.contas.put(numero, novaConta);
        return novaConta;
    }

    public boolean clienteExiste(String cpf){
        return this.clientes.containsKey(cpf);
    }
    //public Conta buscarConta(String numero);     //Aqui é um ótimo lugar para usar Optional<Conta> para tratar casos em que a conta não é encontrada.

    public boolean executarDeposito(String numeroConta, double valor){
        Conta contaParaDepositar = this.contas.get(numeroConta);
        if (contaParaDepositar != null) {
            contaParaDepositar.depositar(valor);
            System.out.println("Depósito no valor de R$" + valor + " realizado com sucesso.");
            return true;
        } else {
            System.out.println("Erro: Conta número " + numeroConta + " não encontrada.");
            return false;
        }
    }

    public boolean executarSaque(String numeroConta, double valor){
        Conta contaParaSacar = this.contas.get(numeroConta);
        if (contaParaSacar != null && contaExiste(numeroConta) && contaParaSacar.getSaldo() >= valor) {
            contaParaSacar.sacar(valor);
            System.out.println("Saque no valor de" + valor + " realizado com sucesso.");
            return true;
        } else {
            System.out.println("Erro: Conta número " + numeroConta + " não encontrada.");
            return false;
        }
    }

    public Cliente getCliente(String cpfTitular) {
        return this.clientes.get(cpfTitular);
    }

    public boolean executarTransferencia(String numContaOrigem, String numContaDestino, double valor) {
        Conta contaParaSacar = this.contas.get(numContaOrigem);
        Conta contaParaDepositar = this.contas.get(numContaDestino);
        if (contaParaSacar != null && contaParaDepositar != null) {
            if (!contaExiste(numContaDestino)) {
                System.out.println("Erro: Conta de destino não encontrada.");
                return false;
            }
            else if (contaParaSacar.getSaldo() >= valor) {
            contaParaSacar.sacar(valor);
            contaParaDepositar.depositar(valor);
            System.out.println("Transferência realizada com sucesso.");
            return true;
            } else {System.out.println("Erro: Saldo insuficiente na conta de origem.");
                return false;
            }
        } else {
            System.out.println("Erro ao realizar operação.");
            return false;
        }
    }

    public boolean contaExiste(String numConta){
        return this.contas.containsKey(numConta);
    }

    public String getTitular(String numeroConta){
        return this.contas.get(numeroConta).getTitular().getNome();
    }

    public void imprimirExtrato(Conta conta){
        if (conta instanceof ContaCorrente){
            ContaCorrente contaCorrente = (ContaCorrente) conta;
            contaCorrente.imprimirExtrato();
        } else if (conta instanceof ContaPoupanca) {
            ContaPoupanca contaPoupanca = (ContaPoupanca) conta;
            contaPoupanca.imprimirExtrato();
        }
    }
    public void fecharConta(String conta){
        Conta contaParaSacar = this.contas.get(conta);
        if (contaParaSacar.getSaldo() == 0){
            this.contas.remove(conta);
            System.out.println("Conta fechada com sucesso.");
        } else {
            System.out.println("Você precisa primeiro sacar os valores da conta.");
        }
    }

    public void fecharCadastro(Cliente cliente){
        List<Conta> contasDoCliente = buscarContaCliente(cliente);
        if (contasDoCliente.isEmpty()) {
            // If the client has no accounts, we can safely remove them from the clientes map
            clientes.remove(cliente.getCpf());
            System.out.println("Cadastro do cliente encerrado com sucesso.");
        } else {
            System.out.println("Cliente ainda possui contas ativas. Encerre todas as contas antes de fechar o cadastro.");
        }

    }

    public List<Conta> buscarContaCliente(Cliente cliente){
        List<Conta> contasDoCliente = new ArrayList<>();
        // Itera por TODAS as contas que o banco gerencia.
        for (Conta conta : this.contas.values()) {
            // Para cada conta, verifica se o CPF do titular é o que estamos procurando.
            if (conta.getTitular().getCpf().equals(cliente.getCpf())) {
                // Se for, adiciona à nossa lista de resultados.
                contasDoCliente.add(conta);
            }
        }
        return contasDoCliente;
    }

}
