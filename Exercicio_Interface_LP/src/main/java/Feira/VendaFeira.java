package Feira;

public class VendaFeira {
    private int idVenda;
    private double valorVenda;
    private String formaPagamento;

    public VendaFeira(int id) {
        this.idVenda = id;
        this.valorVenda = 0.0;
        this.formaPagamento = "Pendente";
    }

    public void registrarItem(double valorItem) {
        this.valorVenda += valorItem;
    }

    // Novo método para definir a forma de pagamento
    public void definirFormaPagamento(String forma) {
        this.formaPagamento = forma;
    }

    public String finalizarVenda() {
        return String.format("Venda #%d | Total: R$ %.2f | Pagamento: %s",
                idVenda, valorVenda, formaPagamento);
    }

    public double getValorVenda() { return valorVenda; }
}