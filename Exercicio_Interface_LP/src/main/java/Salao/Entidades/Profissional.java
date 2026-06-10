package Salao;

public class Profissional {
    int id;
    String nome;
    String especialidade;

    public Profissional(int id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public double calcularComissaoPadrao(double valorServico) {
        return valorServico * 0.30;
    }

    public String obterPerfil() {
        return nome + " (" + especialidade + ")";
    }

    public String getNome() {
        return nome;
    }
}