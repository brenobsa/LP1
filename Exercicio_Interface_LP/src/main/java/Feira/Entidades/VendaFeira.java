package Feira;

public class VendaFeira {
    int idVenda;
    double valorVenda;
    int bancaId;

    public VendaFeira(int id, double valor, int bancaId) {
        this.idVenda = id;
        this.valorVenda = valor;
        this.bancaId = bancaId;
    }

    // Método 1
    public String adicionarItem(String item) {
        return "Item " + item;
    }

    // Método 2
    public String finalizarVenda(String forma) {
        return "Venda #" + idVenda + " de R$ " + String.format("%.2f", valorVenda) + " paga via " + forma;
    }

    // Método 3
    public void cancelarVenda() {
        this.valorVenda = 0;
    }
}