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

    public Conta criarContaCorrente(String cpfTitular){
        Cliente titularDaConta = this.clientes.get(cpfTitular);
        if (titularDaConta == null) {
            System.out.println("Erro: Não é possível criar a conta. Cliente com CPF " + cpfTitular + " não encontrado.");
            return null;
        }
        String agencia = "0001";
        String numero = String.valueOf(this.proximoNumeroConta++);
        Conta novaConta = new ContaCorrente(titularDaConta, agencia, numero);
        this.contas.put(numero, novaConta);
        return novaConta;
    }

    public Conta criarContaPoupanca(String cpfTitular){
        Cliente titularDaConta = this.clientes.get(cpfTitular);
        if (titularDaConta == null) {
            System.out.println("Erro: Não é possível criar a conta. Cliente com CPF " + cpfTitular + " não encontrado.");
            return null;
        }
        String agencia = "0001";
        String numero = String.valueOf(this.proximoNumeroConta++);
        Conta novaConta = new ContaPoupanca(titularDaConta, agencia, numero);
        this.contas.put(numero, novaConta);
        return novaConta;
    }

    public boolean clienteExiste(String cpf){
        this.clientes.containsKey(cpf);
        return this.clientes.containsKey(cpf);
    }
    //public Conta buscarConta(String numero);     //Aqui é um ótimo lugar para usar Optional<Conta> para tratar casos em que a conta não é encontrada.

    public boolean executarDeposito(String numeroConta, double valor){
        Conta contaParaDepositar = this.contas.get(numeroConta);
        if (contaParaDepositar != null) {
            contaParaDepositar.depositar(valor);
            return true;
        } else {
            System.out.println("Erro: Conta número " + numeroConta + " não encontrada.");
            return false;
        }
    }

    public boolean executarSaque(String numeroConta, double valor){
        Conta contaParaSacar = this.contas.get(numeroConta);
        if (contaParaSacar != null) {
            contaParaSacar.sacar(valor);
            return true;
        } else {
            System.out.println("Erro: Conta número " + numeroConta + " não encontrada.");
            return false;
        }
    }

    //public void executarTransferencia(String numContaOrigem, String numContaDestino, double valor);

    public List<Conta> buscarContaCliente(String cpf){
        List<Conta> contasDoCliente = new ArrayList<>();
        // Itera por TODAS as contas que o banco gerencia.
        for (Conta conta : this.contas.values()) {
            // Para cada conta, verifica se o CPF do titular é o que estamos procurando.
            if (conta.getTitular().getCpf().equals(cpf)) {
                // Se for, adiciona à nossa lista de resultados.
                contasDoCliente.add(conta);
            }
        }
        return contasDoCliente;
    }

}
