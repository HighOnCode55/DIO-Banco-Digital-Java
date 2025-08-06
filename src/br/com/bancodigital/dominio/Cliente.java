package br.com.bancodigital.dominio;

public class Cliente {

    // 1. Atributos agora são privados. Só a própria classe pode alterá-los.
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // 2. Getters públicos para permitir a LEITURA dos dados pelo mundo exterior.
    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void corrigirNome(String nomeCorreto){
        this.nome = nomeCorreto;
        System.out.println("Nome atualizado para " + nomeCorreto);
    }

}
