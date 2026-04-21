package Salao;

public class Servico {
    private String descricao; // Ex: Corte, Escova, Coloração
    private double valor;
    private int tempoEstimado; // Em minutos

    public Servico(String descricao, double valor, int tempo) {
        this.descricao = descricao;
        this.valor = valor;
        this.tempoEstimado = tempo;
    }

    public void aplicarTaxaAdicional(double percentual) {
        // Para cabelos longos ou serviços complexos
        this.valor += (this.valor * (percentual / 100));
    }

    public String obterResumoServico() {
        return descricao + " - R$ " + valor + " (" + tempoEstimado + " min)";
    }

    public boolean validarPreco() {
        return valor > 0;
    }

    public double getValor() { return valor; }
    public String getDescricao() { return descricao; }
}
