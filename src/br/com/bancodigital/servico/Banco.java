package br.com.bancodigital.servico;

import br.com.bancodigital.dominio.Cliente;
import br.com.bancodigital.dominio.Conta;

import java.util.HashMap;
import java.util.Map;

public class Banco {
    private Map<String, Conta> contas;
    private Map<String, Cliente> clientes;

    public Banco() {
        this.contas = new HashMap<>();
        this.clientes = new HashMap<>();
    }

    public void criarCliente(String nome, String CPF){
        Cliente novoCliente = new Cliente();
        this.clientes.put(CPF, novoCliente);
    }
    public void criarContaCorrente(String )

    public void criarContaPoupanca;

    public Conta buscarConta(String numero);     //Aqui é um ótimo lugar para usar Optional<Conta> para tratar casos em que a conta não é encontrada.

    public void executarDeposito(String numeroConta, double valor);

    public void executarSaque(String numeroConta, double valor);

    public void executarTransferencia(String numContaOrigem, String numContaDestino, double valor);


}
