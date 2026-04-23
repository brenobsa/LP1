package Salao;

public class Agendamento {
    private String dataHora;
    private String formaPagamento = "Pendente";

    public Agendamento(String dataHora) {
        this.dataHora = dataHora;
    }


    public void definirPagamento(String forma) {
        this.formaPagamento = forma;
    }


    public String confirmarAgendamento(String cliente, String servico) {
        return String.format("Cliente: %s | Serviço: %s | Data: %s | Pagamento: %s",
                cliente, servico, dataHora, formaPagamento);
    }


    public void remarcar(String novaData) {
        this.dataHora = novaData;
    }
}