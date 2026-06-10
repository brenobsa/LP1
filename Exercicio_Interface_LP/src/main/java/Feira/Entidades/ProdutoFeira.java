package Feira;

public class ProdutoFeira {
    int id;
    String nome;
    double precoPorQuilo;

    public ProdutoFeira(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.precoPorQuilo = preco;
    }

    public void aplicarPrecoDesconto(double percentual) {
        this.precoPorQuilo -= this.precoPorQuilo * (percentual / 100);
    }

    public double calcularPrecoPorPeso(double peso) {
        return this.precoPorQuilo * peso;
    }

    public boolean verificarDisponibilidade() {
        return precoPorQuilo > 0;
    }
}