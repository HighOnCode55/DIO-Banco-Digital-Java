package br.com.bancodigital.aplicacao;

import br.com.bancodigital.util.Leitor;
import br.com.bancodigital.servico.Banco;
import java.util.Scanner;

public class Terminal {
    private Banco banco;
    private Leitor leitor = new Leitor();

    // Métodos de ajuda!
    private void print(Object texto) {
        System.out.println(texto);
    }
    private void fechar() {
        leitor.fechar();
    }

    private void acessoCliente() {
        String lerCPF;
        lerCPF = leitor.cpf("Digite o seu CPF: ");
    }



    private void criarCliente() {
        String lerCPF = leitor.cpf("Digite o seu CPF: ");
    }

    private void criarConta() {
        String lerCPF;
        String lerString;

        lerCPF = leitor.cpf("Digite o seu CPF: ");
        lerString = leitor.lerString("Qual tipo de conta gostaria de criar?\n" +
                "1. Conta Corrente\n" +
                "2. Conta Poupança\n");
        if (lerString == 1){

        }
    }

    private void processarDeposito();
    private void processarSaque();
    private void processarTransferencia();
    private void processarImprimirExtrato();


    public Terminal(){
        this.banco = new Banco();
    }

    // O PONTO DE PARTIDA (instrução na "planta")
    public static void main(String[] args) {
        // 1. Construa a "casa real": crie uma instância do Terminal.
        Terminal terminalStart = new Terminal();

        // 2. "Ligue" a casa: chame um método de instância para começar o programa.
        terminalStart.iniciar();
    }

    // MÉTODO DE INSTÂNCIA: Aqui começa a lógica do seu programa.
    // Como este método não é estático, ele PODE usar "this".
    public void iniciar() {
        String respostaMenu;
        respostaMenu = leitor.lerString("Bem vindo ao Banco Digital, insira o número correspondente com a operação desejada.\n" +
                "1. Acesso Cliente.\n" +
                "2. Criar nova conta\n" +
                "3. Sair");
        return

        // Agora sim, podemos usar o scanner da instância!
        // Lógica para tratar a opção do menu
        if (respostaMenu == 1) {
            this.criarCliente();
        } else if (respostaMenu == 2) {
            this.criarConta();
        }
        // ... e assim por diante

        this.fechar(); // Não se esqueça de fechar o scanner no final.
    }

}