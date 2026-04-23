package Feira;

public class ProdutoFeira {
    private String nome;
    private double precoPorQuilo;
    private boolean descontoAplicado = false;

    public ProdutoFeira(String nome, double preco) {
        this.nome = nome;
        this.precoPorQuilo = preco;
    }

    public void aplicarPrecoDesconto(double desconto) {
        if (!descontoAplicado) {
            this.precoPorQuilo -= (this.precoPorQuilo * (desconto / 100));
            this.descontoAplicado = true;
        }
    }

    public double calcularPrecoPorPeso(double pesoDesejado) {
        return this.precoPorQuilo * pesoDesejado;
    }

    public String getNome() { return nome; }
    public double getPrecoPorQuilo() { return precoPorQuilo; }
    public boolean isDescontoAplicado() { return descontoAplicado; }
}