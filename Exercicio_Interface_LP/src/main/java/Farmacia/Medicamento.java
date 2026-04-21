package Farmacia;

public class Medicamento {
    private String nomeGenerico;
    private double preco;
    private int quantidadeEstoque;

    public Medicamento(String nome, double preco, int estoque) {
        this.nomeGenerico = nome;
        this.preco = preco;
        this.quantidadeEstoque = estoque;
    }

    public boolean verificarDisponibilidade() {
        return quantidadeEstoque > 0;
    }

    public void aplicarDesconto(double percentual) {
        this.preco -= this.preco * (percentual / 100);
    }

    public void solicitarReposicao(int quantidade) {
        this.quantidadeEstoque += quantidade;
    }

    // Getters para a interface ler os dados
    public String getNomeGenerico() { return nomeGenerico; }
    public double getPreco() { return preco; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
}