package Farmacia;

public class Medicamento {
    int id;
    String nome;
    double preco;

    public Medicamento(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    // Método 1
    public boolean verificarDisponibilidade() {
        return preco > 0;
    }

    // Método 2
    public void aplicarDesconto(double percentual) {
        this.preco -= this.preco * (percentual / 100);
    }

    // Método 3
    public boolean validarId() {
        return id > 0;
    }
}