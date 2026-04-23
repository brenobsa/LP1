package Salao;

public class Profissional {
    private String nome;
    private String especialidade;
    private double comissaoPercentual;

    public Profissional(String nome, String especialidade, double comissao) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.comissaoPercentual = comissao;
    }


    public double calcularComissao(double valorServico) {
        return valorServico * (this.comissaoPercentual / 100);
    }


    public String obterPerfil() {
        return nome + " (" + especialidade + ")";
    }


    public String getNome() { return nome; }
}