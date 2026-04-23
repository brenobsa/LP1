package Feira;

public class VendaFeira {
    private int idVenda;
    private double valorVenda = 0.0;
    private String formaPagamento = "Pendente";

    public VendaFeira(int id) {
        this.idVenda = id;
    }

    public void registrarItem(double valorItem) {
        this.valorVenda += valorItem;
    }

    public void definirFormaPagamento(String forma) {
        this.formaPagamento = forma;
    }

    public String finalizarVenda() {
        return String.format("Venda #%d Finalizada | Total: R$ %.2f | Pago via: %s",
                idVenda, valorVenda, formaPagamento);
    }

    public double getValorVenda() { return valorVenda; }
}