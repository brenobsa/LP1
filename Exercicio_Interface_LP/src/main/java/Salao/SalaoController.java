package Salao;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class SalaoController {
    private List<Servico> listaServicos = new ArrayList<>();
    private List<Profissional> listaEquipe = new ArrayList<>();
    private Agendamento agendamentoAtivo;

    public void configurarAbaServico(VBox layout) {
        TextField txtDesc = new TextField(); txtDesc.setPromptText("Descrição");
        TextField txtPreco = new TextField(); txtPreco.setPromptText("Preço Base");
        Label lblStatus = new Label("Status: Aguardando");

        Button btnAdd = new Button("Cadastrar com Taxa (10%)");
        btnAdd.setOnAction(e -> {
            try {
                Servico s = new Servico(txtDesc.getText(), Double.parseDouble(txtPreco.getText()));
                s.aplicarTaxaAdicional(10); // Método 1 de Servico
                listaServicos.add(s);
                lblStatus.setText("Cadastrado: " + s.obterResumoServico()); // Método 2 de Servico
            } catch (Exception ex) { lblStatus.setText("Erro nos valores."); }
        });

        layout.getChildren().addAll(new Label("Serviços"), txtDesc, txtPreco, btnAdd, lblStatus);
    }

    public void configurarAbaProfissional(VBox layout) {
        TextField txtNome = new TextField(); txtNome.setPromptText("Nome do Profissional");
        ComboBox<String> cbEsp = new ComboBox<>(); cbEsp.getItems().addAll("Cabeleireiro", "Manicure");
        Label lblProf = new Label("Aguardando...");

        Button btnSalvar = new Button("Registrar e Calcular Comissão");
        btnSalvar.setOnAction(e -> {
            if (!txtNome.getText().isEmpty() && cbEsp.getValue() != null) {
                Profissional p = new Profissional(txtNome.getText(), cbEsp.getValue(), 30.0);
                listaEquipe.add(p);
                double comissao = p.calcularComissao(100.0); // Método 1 de Profissional
                lblProf.setText(p.obterPerfil() + " - Comissão s/ R$100: R$ " + comissao); // Método 2 de Profissional
            } else { lblProf.setText("Preencha todos os campos."); }
        });

        layout.getChildren().addAll(new Label("Equipe"), txtNome, cbEsp, btnSalvar, lblProf);
    }

    public void configurarAbaAgendamento(VBox layout) {
        TextField txtCli = new TextField(); txtCli.setPromptText("Nome do Cliente");
        TextField txtData = new TextField(); txtData.setPromptText("Data/Hora");
        ComboBox<String> cbPag = new ComboBox<>(); cbPag.getItems().addAll("Pix", "Cartão", "Dinheiro");
        Label lblStatus = new Label("Sem agendamento.");

        Button btnAgendar = new Button("Confirmar Agendamento");
        btnAgendar.setOnAction(e -> {
            agendamentoAtivo = new Agendamento(txtData.getText());
            agendamentoAtivo.definirPagamento(cbPag.getValue()); // Método 1 de Agendamento
            lblStatus.setText(agendamentoAtivo.confirmarAgendamento(txtCli.getText(), "Serviço Geral")); // Método 2 de Agendamento
        });

        Button btnRemarcar = new Button("Remarcar para Amanhã");
        btnRemarcar.setOnAction(e -> {
            if (agendamentoAtivo != null) {
                agendamentoAtivo.remarcar("Amanhã às 10:00"); // Método 3 de Agendamento
                lblStatus.setText("Atualizado: " + agendamentoAtivo.confirmarAgendamento(txtCli.getText(), "Serviço Geral"));
            } else { lblStatus.setText("Nenhum agendamento para remarcar."); }
        });

        layout.getChildren().addAll(new Label("Agenda"), txtCli, txtData, cbPag, btnAgendar, btnRemarcar, lblStatus);
    }
}