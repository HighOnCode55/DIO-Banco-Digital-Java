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
    public String lerString(String prompt) {
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
        while (true) {
            // 1. Primeiro, lemos a entrada do usuário como uma String normal.
            String cpfComFormatacao = lerString(prompt);

            // 2. Usamos replaceAll para remover tudo que não for um dígito.
            String cpfSanitizado = cpfComFormatacao.replaceAll("[^0-9]", "");

            if (cpfSanitizado.length() == 11) {
                // Se o CPF tem 11 dígitos, ele é válido!
                // O 'return' faz duas coisas: retorna o valor e sai do método (e do laço).
                return cpfSanitizado;
            } else {
                // Se não, avisamos o usuário e o laço 'while' continua,
                // pedindo a entrada novamente.
                print("Erro: O CPF deve conter 11 dígitos. Você inseriu " + cpfSanitizado.length() + " dígitos. Tente novamente.");
            }
        }
    }

    // Você pode criar um lerDouble() seguindo a mesma lógica
    public double lerDouble(String prompt) {
        while (true) {
            String linha = lerString(prompt);
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
