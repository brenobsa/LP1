package Farmacia;

public class Pedido {
    private int numero;
    private double valor;

    public Pedido(int numero, double valor) {
        this.numero = numero;
        this.valor = valor;
    }

    public String adicionarItem(String item) {
        return "Item " + item + " adicionado ao #" + numero;
    }

    public String finalizarPagamento(String metodo) {
        return "Pedido #" + numero + " (R$" + valor + ") pago via " + metodo;
    }

    public void cancelarPedido() { this.valor = 0; }
}