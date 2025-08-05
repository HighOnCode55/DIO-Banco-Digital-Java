package br.com.bancodigital.aplicacao;

import br.com.bancodigital.dominio.Cliente;
import br.com.bancodigital.dominio.Conta;
import br.com.bancodigital.dominio.ContaCorrente;
import br.com.bancodigital.util.Leitor;
import br.com.bancodigital.servico.Banco;

import java.util.List;

public class Terminal {
    private Banco banco = new Banco();
    private Leitor leitor = new Leitor();
    private Conta contaSelecionada = null;
    private Cliente cliente = null;

    private String nomeCliente(){
        return cliente.getNome();
    }

    private void logarCliente(String cpf){
        cliente = banco.getCliente(cpf);
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
        lerCPF = leitor.cpf("Digite o seu CPF ou 0 para voltar");
        if (lerCPF.equals("0")) {
            menuPrincipal();
        }
        else if (banco.clienteExiste(lerCPF)) {
            logarCliente(lerCPF);
            menuCliente();
        } else {
            print("Cliente não encontrado, tente novamente ou 0 para voltar ao menu principal.");
            acessoCliente();
        }
    }

    //menuCliente leva laço while pois leitor.ler não contém o mesmo
    private void menuCliente() {
        if (cliente != null) while (true) {
            String ler = leitor.ler("Bem vindo, " + nomeCliente() + ". Do que precisa hoje?\n" +
                    "1. Gerenciar Contas\n" +
                    "2. Gerenciar Cadastro\n" +
                    "3. Sair\n");
            if (ler.equals("1")) {
                gerenciarContas();
            }
            else if (ler.equals("2")) {
                gerenciarCadastro();
            }
            else if (ler.equals("3")) {
                cliente = null;
                menuPrincipal();
            } else {
                print("Tente novamente.");
                //menuCliente();
            }
        }
    }

    private void gerenciarContas() {
        while (true) {
            print("\n--- Seleção de Contas para " + nomeCliente() + " ---");

            // 1. Busca a lista de contas ATUALIZADA a cada iteração do laço.
            List<Conta> contasDoCliente = banco.buscarContaCliente(cliente);

            if (contasDoCliente.isEmpty()) {
                print("Você ainda não possui contas.");
            } else {
                print("Suas contas:");
                // 2. Exibe as contas existentes, numeradas de 1 a N.
                for (int i = 0; i < contasDoCliente.size(); i++) {
                    Conta conta = contasDoCliente.get(i);
                    String tipoConta = (conta instanceof ContaCorrente) ? "Conta Corrente" : "Conta Poupança";
                    print(String.format("%d. %s | Número: %s | Saldo: R$ %.2f",
                            (i + 1), tipoConta, conta.getNumero(), conta.getSaldo()));
                }
            }

            print("---------------------------------------");
            // 3. Adiciona as OPÇÕES DE AÇÃO ao final do menu.
            print((contasDoCliente.size() + 1) + ". Criar Nova Conta");
            print("0. Voltar ao Menu Anterior");

            int escolha = leitor.lerInt("Digite sua opção: ");

            // 4. Processa a escolha do usuário.
            if (escolha == 0) {
                // Se escolheu 0, sai do laço e volta para o menu do cliente.
                break;
            } else if (escolha == (contasDoCliente.size() + 1)) {
                // Se escolheu a opção de criar conta, chama o método apropriado.
                // O laço 'while' vai continuar, mostrando a lista atualizada.
                criarConta();
            } else if (escolha > 0 && escolha <= contasDoCliente.size()) {
                // Se escolheu uma conta existente, seleciona e vai para o menu de operações.
                this.contaSelecionada = contasDoCliente.get(escolha - 1);
                menuConta();
                // Após sair do menuDaConta, podemos querer voltar ao menu do cliente, então saímos do laço.
                break;
            } else {
                print("Erro: Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuConta(){
        if (cliente != null && contaSelecionada != null) while (true) {
            String ler = leitor.ler("Bem vindo, " + nomeCliente() + ". Do que precisa hoje?\n" +
                    "1. Depositar\n" +
                    "2. Sacar\n" +
                    "3. Transferir\n" +
                    "4. Fechar conta\n" +
                    "0. Voltar\n");
            if (ler.equals("1")) {
                double lerDeposito = leitor.lerDouble("Digite o valor a ser depositado: ");
                banco.executarDeposito(contaSelecionada.getNumero(), lerDeposito);

            }
            else if (ler.equals("2")) {
                gerenciarCadastro();
            }
            else if (ler.equals("3")) {
                cliente = null;
                menuPrincipal();
            } else {
                print("Tente novamente.");
                //menuCliente();
            }
        }
    }

    private void gerenciarCadastro() {
        if (cliente != null) while (true) {
            String ler = leitor.ler("Bem vindo, " + nomeCliente() + ". Do que precisa hoje?\n" +
                    "1. Corrigir nome\n" +
                    "2. Fechar Conta\n" +
                    "0. Voltar\n");
            if (ler.equals("1")) {
                gerenciarContas();
            }
            else if (ler.equals("2")) {
                gerenciarCadastro();
            }
            else if (ler.equals("0")) {
                menuCliente();
            } else {
                print("Tente novamente.");
                //menuCliente();
            }
        }
    }

    private void criarCliente() {
        String lerCPF = leitor.cpf("Digite o seu CPF ou 0 para voltar.");
        if (lerCPF.equals("0")) {
            menuPrincipal();
        } else if (banco.clienteExiste(lerCPF)) {
            System.out.println("Aviso: Cliente com CPF " + lerCPF + " já está cadastrado.");
        } else {
            String lerNome = leitor.ler("Digite o seu nome: ");
            Cliente novoCliente = banco.criarCliente(lerNome, lerCPF);
            cliente = novoCliente;
            if (cliente != null) {
                print("Cliente " + cliente.getNome() + " criado com sucesso.");
            } else {
                print("Erro ao criar cliente.");
            }
            Boolean resposta = leitor.lerSN("Gostaria de criar uma conta? (S/N)\n");
            if (resposta == true) {
                criarConta();
            } else {
                print("Obrigado por ser cliente do nosso banco!");
                cliente = null;
                menuPrincipal();
            }
        }
    }

    private void criarConta() {
        if (cliente != null) while (true) {
            String ler = leitor.ler("Qual tipo de conta gostaria de criar?\n" +
                    "1. Conta Corrente\n" +
                    "2. Conta Poupança\n" +
                    "3. Voltar\n");
            if (ler.equals("1")) {
                Conta resultado = banco.criarContaCorrente(cliente);
                print("Conta nº" + resultado.getNumero() + " criada com sucesso!");
                menuCliente();
            }
            else if (ler.equals("2")) {
                Conta resultado = banco.criarContaPoupanca(cliente);
                print("Conta nº" + resultado.getNumero() + " criada com sucesso!");
                menuCliente();
            }
            else if (ler.equals("3")) {
                menuCliente();
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
        while (true) {
            String ler;
            ler = leitor.ler("Bem vindo ao Banco Digital, selecione a operação desejada.\n" +
                    "1. Acesso Cliente.\n" +
                    "2. Criar nova conta\n" +
                    "3. Fechar APP\n");

            // Agora sim, podemos usar o scanner da instância!
            // Lógica para tratar a opção do menu
            if (ler.equals("1")) {
                acessoCliente();
            } else if (ler.equals("2")) {
                criarCliente();
            } else if (ler.equals("3")) {
                print("Obrigado por usar nosso banco digital!");
                fechar();
                break;
            } else {
                print("Tente novamente.");
            }
        }
    }

}