package Farmacia;

public class Pedido {
    int numero;
    double valor;
    String clienteCpf;

    public Pedido(int numero, double valor) {
        this.numero = numero;
        this.valor = valor;
        this.clienteCpf = "";
    }

    // Método 1
    public String adicionarItem(String item) {
        return "Item ID " + item + " adicionado ao #" + numero;
    }

    // Método 2
    public String finalizarPagamento(String metodo) {
        return "Pedido #" + numero + " (R$" + valor + ") pago via " + metodo;
    }

    // Método 3
    public void cancelarPedido() {
        this.valor = 0;
    }
}