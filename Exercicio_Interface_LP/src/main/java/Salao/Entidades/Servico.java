package Salao;

public class Servico {
    int id;
    String descricao;
    double valor;

    public Servico(int id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public void aplicarTaxaAdicional(double percentual) {
        this.valor += (this.valor * (percentual / 100));
    }

    public String obterResumoServico() {
        return String.format("%s - R$ %.2f", descricao, valor);
    }

    public boolean validarPreco() {
        return valor > 0;
    }
}