package br.com.bancodigital.servico;

import br.com.bancodigital.dominio.Conta;

import java.util.Map;

public class Banco {
    private Map<String, Conta> contas;

    public void criarContaCorrente;

    public void criarContaPoupanca;

    public Conta buscarConta(String numero);     //Aqui é um ótimo lugar para usar Optional<Conta> para tratar casos em que a conta não é encontrada.

    public void executarDeposito(String numeroConta, double valor);

    public void executarSaque(String numeroConta, double valor);

    public void executarTransferencia(String numContaOrigem, String numContaDestino, double valor);


}
