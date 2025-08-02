package br.com.bancodigital.util;

import java.util.Scanner;

public class Leitor {

    // Métodos de ajuda!
    private void print(Object texto) {
        System.out.println(texto);
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

    public String lerCpf(String prompt){
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Pede ao usuário para digitar um CPF e retorna apenas os números.
     * Remove pontos, traços e quaisquer outros caracteres não numéricos.
     * @param prompt A mensagem a ser exibida para o usuário.
     * @return O CPF contendo apenas os dígitos.
     */
    public String cpf(String prompt) {
            String cpfInput = lerCpf(prompt);
            String cpfSanitizado = lerCpf(prompt).replaceAll("[^0-9]", "");

            if (cpfSanitizado.length() == 11) {
                return cpfSanitizado;
            } else {
                print("Erro: O CPF deve conter 11 dígitos. Você inseriu " + cpfSanitizado.length() + " dígitos. Tente novamente.");
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

    public void fechar() {
        scanner.close();
    }
}
