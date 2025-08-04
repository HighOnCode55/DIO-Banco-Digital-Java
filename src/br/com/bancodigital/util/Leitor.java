package br.com.bancodigital.util;

import br.com.bancodigital.dominio.Conta;
import br.com.bancodigital.dominio.ContaCorrente;

import java.util.List;
import java.util.Scanner;

public class Leitor {

    // Métodos de ajuda!
    private String print(Object texto) {
        System.out.println(texto);
        return null;
    }

    // O Scanner agora é privado dentro desta classe de utilidade.
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Pede ao usuário para digitar uma String e a retorna.
     * @param prompt A mensagem a ser exibida para o usuário.
     * @return A String digitada pelo usuário.
     */
    public Integer lerInt(String prompt) {
        System.out.print(prompt);
        String trim = scanner.nextLine().trim();
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Por favor digite um número inteiro válido.");
            return lerInt(prompt); // recursively try again
        }
    }


    public Boolean lerSN(String prompt) {
        while(true) {
            System.out.print(prompt);
            String respostaSN = scanner.nextLine().trim();
            if (respostaSN.equalsIgnoreCase("s")) {
                return true;
            }
            if (respostaSN.equalsIgnoreCase("n")) {
                return false;
            }
        }
    }

    public String ler(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
/*
    public String lerCpf(String prompt){
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
*/
    /**
     * Pede ao usuário para digitar um CPF e retorna apenas os números.
     * Remove pontos, traços e quaisquer outros caracteres não numéricos.
     * @param prompt A mensagem a ser exibida para o usuário.
     * @return O CPF contendo apenas os dígitos.
     */
    public String cpf(String prompt) {
        while (true) {
            print(prompt);
            String cpfSanitizado = scanner.nextLine().trim().replaceAll("[^0-9]", "");

            if (cpfSanitizado.equals("0")) {
                return cpfSanitizado;
            } else if (cpfSanitizado.length() == 11) {
                return cpfSanitizado;
            } else {
                print("Erro: O CPF deve conter 11 dígitos. Você inseriu " + cpfSanitizado.length() + " dígitos. Tente novamente.");
            }
        }
    }
    // Você pode criar um lerDouble() seguindo a mesma lógica
    public double lerDouble(String prompt) {
        while (true) {
            print(prompt);
            String linha = scanner.nextLine().trim();
            try {
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número (ex: 50.75).");
            }
        }
    }

    public Conta selecionarConta(List<Conta> contas, String prompt) {
        while (true) {
            System.out.println("\n--- Por favor, selecione uma conta ---");

            // 1. Exibe o menu numerado. Usamos um 'for' com índice aqui.
            for (int i = 0; i < contas.size(); i++) {
                Conta conta = contas.get(i);
                String tipoConta = (conta instanceof ContaCorrente) ? "Conta Corrente" : "Conta Poupança";

                // Imprime a opção. Ex: "1. Conta Corrente | Ag: 0001 | Num: 123"
                System.out.printf("%d. %s | Número: %s\n",
                        (i + 1), // Mostra o número para o usuário (começando em 1)
                        tipoConta,
                        conta.getNumero());
            }

            // 2. Pede ao usuário para digitar o número.
            int escolha = this.lerInt(prompt);

            // 3. Valida a escolha.
            //    Verifica se a escolha está entre 1 e o tamanho da lista.
            if (escolha >= 1 && escolha <= contas.size()) {
                // 4. Se a escolha é válida, retorna o objeto Conta correto.
                //    Lembre-se que listas começam em 0, então usamos 'escolha - 1'.
                return contas.get(escolha - 1);
            } else {
                // Se a escolha for inválida, mostra um erro e o laço 'while' repete o menu.
                System.out.println("Erro: Opção inválida. Por favor, digite um número da lista.");
            }
        }
    }

    public void fechar() {
        scanner.close();
    }
}
