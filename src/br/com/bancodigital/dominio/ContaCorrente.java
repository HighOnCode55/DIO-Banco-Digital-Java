package br.com.bancodigital.dominio;

public class ContaCorrente extends Conta{
    public ContaCorrente(Cliente titular, String agencia, String numero) {
        super(titular, agencia, numero);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println(titular.getNome() + "\n"
                + agencia + " - " + numero + "\n"
                + "Saldo: R$" + saldo + "\n");
    }
}
