package br.com.bancodigital.dominio;

public class ContaPoupanca extends Conta{
    public ContaPoupanca(Cliente titular, String agencia, String numero) {
        super(titular, agencia, numero); // Passa os dados para o construtor da classe Conta.
    }
    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente ===");
        // ...
    }
}
