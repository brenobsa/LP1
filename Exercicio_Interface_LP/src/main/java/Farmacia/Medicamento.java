package Farmacia;

public class Medicamento {
    private String nome;
    private double preco;
    private int estoque;

    public Medicamento(String nome, double preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public boolean verificarDisponibilidade() { return estoque > 0; }

    public void aplicarDesconto(double percentual) {
        this.preco -= this.preco * (percentual / 100);
    }

    public void solicitarReposicao(int qtd) { this.estoque += qtd; }

    public String getNomeGenerico() { return nome; }
    public double getPreco() { return preco; }
    public int getQuantidadeEstoque() { return estoque; }
}