package br.com.bancodigital.dominio;


import br.com.bancodigital.excessao.SaldoException;
import br.com.bancodigital.util.Validar;

public abstract class Conta {

    protected String agencia;
    protected String numero;
    protected double saldo;
    protected Cliente titular;



    public Conta(Cliente titular, String agencia, String numero) {
        this.titular = titular;
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = 0;
    }

    public void depositar(double valor){
        Validar.numeroPositivo(valor, "O valor deve ser positivo.");
        this.saldo += valor;
    }

    public void sacar(double valor){
        Validar.numeroPositivo(valor, "O valor deve ser positivo.");
        if(this.saldo >= valor){
            this.saldo -= valor;
        } else throw new SaldoException("Saldo insuficiente.");
    }

    public void transferir(double valor, Conta contaDestino){
        Validar.numeroPositivo(valor, "O valor deve ser positivo.");
        if (this.saldo >= valor) {
            this.sacar(valor);
            contaDestino.depositar(valor);
            System.out.println("Transferencia no valor de R$" + valor + " realizada com sucesso.");
        } else throw new SaldoException("Saldo insuficiente.");
    }

    public double getSaldo(){
        return this.saldo;
    }

    public abstract void imprimirExtrato();

    public String getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }
}