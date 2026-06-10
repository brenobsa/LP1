package Salao;

public class Agendamento {
    int id;
    String dataHora;
    String formaPagamento;

    public Agendamento(int id, String dataHora) {
        this.id = id;
        this.dataHora = dataHora;
        this.formaPagamento = "Pendente";
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