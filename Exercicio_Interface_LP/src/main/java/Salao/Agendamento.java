package Salao;

public class Agendamento {
    private int idAgendamento;
    private String dataHora;
    private String formaPagamento;

    public Agendamento(int id, String dataHora) {
        this.idAgendamento = id;
        this.dataHora = dataHora;
        this.formaPagamento = "Pendente";
    }

    public void definirPagamento(String forma) {
        this.formaPagamento = forma;
    }

    public String confirmarAgendamento(String cliente, String profissional, String servico) {
        return String.format("Agendado: %s com %s para %s. Pago via: %s",
                dataHora, profissional, servico, formaPagamento);
    }

    public void remarcar(String novaData) {
        this.dataHora = novaData;
    }
}
