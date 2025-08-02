package br.com.bancodigital.aplicacao;

import br.com.bancodigital.dominio.Cliente;
import br.com.bancodigital.dominio.Conta;
import br.com.bancodigital.util.Leitor;
import br.com.bancodigital.servico.Banco;

public class Terminal {
    private Banco banco = new Banco();
    private Leitor leitor = new Leitor();
    private String clienteLogado = null;
    private Conta contaSelecionada = null;
    private Cliente cliente;

    private String nomeCliente(){
        String nome;
        nome = cliente.getNome();
        return nome;
    }

    // Métodos de ajuda!
    private void print(Object texto) {
        System.out.println(texto);
    }
    private void fechar() {
        leitor.fechar();
    }

    //acessoCliente não leva laço while pois leitor.cpf já tem laço while para garantir entrada de cpf válido
    private void acessoCliente() {
        String lerCPF;
        lerCPF = leitor.cpf("Digite o seu CPF ou 0 para sair:");
        if (lerCPF.equals("0")) {menuPrincipal();}
        if (banco.clienteExiste(lerCPF)) {
            clienteLogado = lerCPF;
            menuCliente();
        } else {print("Cliente não encontrado, tente novamente ou 0 para voltar ao menu principal.");
        acessoCliente();}
    }

    //menuCliente leva laço while pois leitor.ler não contém o mesmo
    private void menuCliente() {
        if (clienteLogado != null) while (true) {
            Integer ler = leitor.lerInt("Bem vindo, " + nomeCliente() + ". Do que precisa hoje?\n" +
                    "1. Gerenciar Contas\n" +
                    "2. Gerenciar Cadastro\n" +
                    "3. Sair");
            if (ler.equals("1")) {
                gerenciarContas();
            }
            else if (ler.equals("2")) {
                gerenciarCadastro();
            }
            else if (ler.equals("3")) {
                clienteLogado = null;
                menuPrincipal();
            } else {
                print("Tente novamente.");
                //menuCliente();
            }
        }
    }

    private void gerenciarContas(){

    }

    private void gerenciarCadastro() {
        if (clienteLogado != null) while (true) {
            Integer ler = leitor.lerInt("Bem vindo, " + nomeCliente() + ". Do que precisa hoje?\n" +
                    "1. Corrigir nome\n" +
                    "2. Fechar Conta\n" +
                    "3. Voltar");
            if (ler.equals("1")) {
                gerenciarContas();
            }
            else if (ler.equals("2")) {
                gerenciarCadastro();
            }
            else if (ler.equals("3")) {
                clienteLogado = null;
                menuPrincipal();
            } else {
                print("Tente novamente.");
                //menuCliente();
            }
        }
    }

    private void criarCliente() {
        String lerCPF = leitor.cpf("Digite o seu CPF: ");
        String lerNome = leitor.ler("Digite o seu nome: ");
        Cliente novoCliente = banco.criarCliente(lerNome, lerCPF);
        clienteLogado = lerCPF;
        cliente = novoCliente;
        if (clienteLogado != null) {
            print("Cliente " + cliente.getNome() + " criado com sucesso.");
        } else {
            print("Erro ao criar cliente.");
        }
        Boolean resposta = leitor.lerSN("Gostaria de criar uma conta? (S/N)");
        if (resposta == true) {
            criarConta();
        } else {
            print("Obrigado por ser cliente do nosso banco!");
            clienteLogado = null;
            menuPrincipal();
        }
    }

    private void criarConta() {
        if (clienteLogado != null) while (true) {
            String ler = leitor.ler("Qual tipo de conta gostaria de criar?\n" +
                    "1. Conta Corrente\n" +
                    "2. Conta Poupança\n" +
                    "3. Voltar\n");
            if (ler.equals("1")) {
                banco.criarContaCorrente(clienteLogado);
            }
            else if (ler.equals("2")) {

            }
            else if (ler.equals("3")) {
                clienteLogado = null;
                menuPrincipal();
            } else {
                print("Tente novamente.");
                //menuCliente();
            }
        } else {
            print("erro de conta");
            menuPrincipal();}
    }

    //private void processarDeposito();
    //private void processarSaque();
    //private void processarTransferencia();
    //private void processarImprimirExtrato();


    public Terminal(){
        this.banco = new Banco();
    }

    // O PONTO DE PARTIDA (instrução na "planta")
    public static void main(String[] args) {
        // 1. Construa a "casa real": crie uma instância do Terminal.
        Terminal terminalStart = new Terminal();

        // 2. "Ligue" a casa: chame um método de instância para começar o programa.
        terminalStart.menuPrincipal();
    }

    // MÉTODO DE INSTÂNCIA: Aqui começa a lógica do seu programa.
    // Como este método não é estático, ele PODE usar "this".
    public void menuPrincipal() {
        String ler;
        ler = leitor.ler("Bem vindo ao Banco Digital, insira o número correspondente com a operação desejada.\n" +
                "1. Acesso Cliente.\n" +
                "2. Criar nova conta\n" +
                "3. Sair\n");

        // Agora sim, podemos usar o scanner da instância!
        // Lógica para tratar a opção do menu
        while (true)
            if (ler.equals("1")) {
            acessoCliente();
        } else if (ler.equals("2")) {
            criarCliente();
        } else if (ler.equals("3")){
            print("Obrigado por usar nosso banco digital!");
            fechar();
        } else {
            print("Tente novamente.");
        }
    }

}