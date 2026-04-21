package Feira;

public class ProdutoFeira {
    private String nome;
    private double precoPorQuilo;
    private double pesoEstoque; // Em kg

    public ProdutoFeira(String nome, double preco, double estoque) {
        this.nome = nome;
        this.precoPorQuilo = preco;
        this.pesoEstoque = estoque;
    }

    public boolean verificarPerecibilidade(int diasParaVencer) {
        return diasParaVencer < 3; // Lógica simples: se vence em menos de 3 dias, é crítico
    }

    public void aplicarPrecoDesconto(double desconto) {
        this.precoPorQuilo -= (this.precoPorQuilo * (desconto / 100));
    }

    public double calcularPrecoPorPeso(double pesoDesejado) {
        return this.precoPorQuilo * pesoDesejado;
    }

    // Getters para a interface
    public String getNome() { return nome; }
    public double getPrecoPorQuilo() { return precoPorQuilo; }
}