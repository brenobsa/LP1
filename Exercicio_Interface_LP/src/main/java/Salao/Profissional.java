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
        return valorServico * (comissaoPercentual / 100);
    }

    public String obterPerfil() {
        return nome + " (" + especialidade + ")";
    }

    public void atualizarComissao(double novaComissao) {
        this.comissaoPercentual = novaComissao;
    }
}