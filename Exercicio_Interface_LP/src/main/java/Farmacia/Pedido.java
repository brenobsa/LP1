package Farmacia;

public class Pedido {
    private int numeroPedido;
    private String dataVenda;
    private double valorTotal;

    public Pedido(int numero, String data, double valor) {
        this.numeroPedido = numero;
        this.dataVenda = data;
        this.valorTotal = valor;
    }

    public String adicionarItem(String item) {
        return "Item " + item + " adicionado ao pedido #" + numeroPedido;
    }

    public String finalizarPagamento(String metodo) {
        return "Venda de R$" + valorTotal + " paga via " + metodo;
    }

    public void cancelarPedido() {
        this.valorTotal = 0;
    }
}
