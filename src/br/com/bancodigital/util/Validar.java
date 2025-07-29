package br.com.bancodigital.util;

public final class Validar {
    private Validar() {
    }

    /**
     * Verifica se um valor é positivo. Lança uma exceção se o valor for zero ou negativo.
     * @param valor O valor a ser verificado.
     * @param mensagem A mensagem de erro a ser usada na exceção.
     */

    public static void numeroPositivo(double valor, String mensagem) {
        if (valor <= 0) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    // Você pode até criar uma versão sobrecarregada com uma mensagem padrão
    public static void numeroPositivo(double valor) {
        numeroPositivo(valor, "O valor não pode ser negativo.");
    }


}
