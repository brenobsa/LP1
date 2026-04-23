package Salao;

public class Servico {
    private String descricao;
    private double valor;

    public Servico(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }


    public void aplicarTaxaAdicional(double percentual) {
        this.valor += (this.valor * (percentual / 100));
    }


    public String obterResumoServico() {
        return String.format("%s - R$ %.2f", descricao, valor);
    }


    public String getDescricao() { return descricao; }
}