package Salao;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class SalaoController {

    public void configurarAbaServico(VBox layout) {
        TextField txtDesc = new TextField();
        txtDesc.setPromptText("Ex: Corte Masculino");
        TextField txtPreco = new TextField();
        txtPreco.setPromptText("Preço (R$)");
        Label lblRes = new Label();

        Button btnAdd = new Button("Cadastrar Serviço");
        btnAdd.setOnAction(e -> {
            try {
                Servico s = new Servico(txtDesc.getText(), Double.parseDouble(txtPreco.getText()), 40);
                lblRes.setText("Serviço: " + s.obterResumoServico());
            } catch (Exception ex) { lblRes.setText("Erro nos dados!"); }
        });

        layout.getChildren().addAll(new Label("Serviços Disponíveis"), txtDesc, txtPreco, btnAdd, lblRes);
    }

    public void configurarAbaProfissional(VBox layout) {
        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome do Profissional");
        ComboBox<String> cbEsp = new ComboBox<>();
        cbEsp.getItems().addAll("Cabeleireiro(a)", "Manicure", "Barbeiro", "Esteticista");
        cbEsp.setValue("Cabeleireiro(a)");
        Label lblPerfil = new Label();

        Button btnVer = new Button("Ver Perfil Profissional");
        btnVer.setOnAction(e -> {
            Profissional p = new Profissional(txtNome.getText(), cbEsp.getValue(), 30.0);
            lblPerfil.setText("Profissional: " + p.obterPerfil());
        });

        layout.getChildren().addAll(new Label("Equipe do Salão"), txtNome, cbEsp, btnVer, lblPerfil);
    }

    public void configurarAbaAgendamento(VBox layout) {
        TextField txtData = new TextField();
        txtData.setPromptText("Data e Hora (Ex: 21/04 14:00)");

        ComboBox<String> cbPgto = new ComboBox<>();
        cbPgto.getItems().addAll("Dinheiro", "Pix", "Cartão", "App Salão");
        cbPgto.setValue("Pix");

        Label lblStatus = new Label();

        Button btnConfirmar = new Button("Finalizar Agendamento");
        btnConfirmar.setOnAction(e -> {
            Agendamento a = new Agendamento(1, txtData.getText());
            a.definirPagamento(cbPgto.getValue());
            // Simulação de confirmação com dados fixos para teste
            lblStatus.setText(a.confirmarAgendamento("Cliente Teste", "Pro Teste", "Corte"));
        });

        layout.getChildren().addAll(new Label("Novo Agendamento"), txtData, cbPgto, btnConfirmar, lblStatus);
    }
}