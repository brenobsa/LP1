package Salao;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class SalaoController {

    private ServicoDAO servicoDAO = new ServicoDAO();
    private ProfissionalDAO profissionalDAO = new ProfissionalDAO();
    private AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
    private Agendamento agendamentoAtivo = null;
    private String nomeClienteAtual = "";
    private String resumoServicoAtual = "";

    public void configurarAbaServico(VBox layout) {
        layout.setPadding(new Insets(15));
        layout.setSpacing(8);

        TextField txtId = new TextField(); txtId.setPromptText("ID do Serviço (Numérico)");
        TextField txtDesc = new TextField(); txtDesc.setPromptText("Descrição");
        TextField txtPreco = new TextField(); txtPreco.setPromptText("Preço Base");
        Label lblStatus = new Label("Status: Aguardando");

        Button btnAdd = new Button("Cadastrar com Taxa (10%)");
        btnAdd.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                double preco = Double.parseDouble(txtPreco.getText());
                Servico s = new Servico(id, txtDesc.getText(), preco);

                s.aplicarTaxaAdicional(10);

                if (s.validarPreco()) {
                    Servico existente = servicoDAO.buscarPorId(s.id);
                    if (existente == null) {
                        servicoDAO.salvar(s);
                        lblStatus.setText("Cadastrado: " + s.obterResumoServico());
                    } else {
                        servicoDAO.atualizar(s);
                        lblStatus.setText("Atualizado: " + s.obterResumoServico());
                    }
                } else {
                    lblStatus.setText("Erro: Preço inválido.");
                }
            } catch (Exception ex) {
                lblStatus.setText("Erro nos valores digitados.");
            }
        });

        layout.getChildren().addAll(new Label("--- GESTÃO DE SERVIÇOS ---"), txtId, txtDesc, txtPreco, btnAdd, lblStatus);
    }

    public void configurarAbaProfissional(VBox layout) {
        layout.setPadding(new Insets(15));
        layout.setSpacing(8);

        TextField txtIdProf = new TextField(); txtIdProf.setPromptText("ID do Profissional (Numérico)");
        TextField txtNome = new TextField(); txtNome.setPromptText("Nome do Profissional");
        ComboBox<String> cbEsp = new ComboBox<>(); cbEsp.getItems().addAll("Cabeleireiro", "Manicure");
        cbEsp.setPromptText("Especialidade");
        Label lblProf = new Label("Aguardando...");

        Button btnSalvar = new Button("Registrar e Simular Comissão");
        btnSalvar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtIdProf.getText());
                String nome = txtNome.getText();
                String esp = cbEsp.getValue();

                if (!nome.isEmpty() && esp != null) {
                    Profissional p = new Profissional(id, nome, esp);
                    profissionalDAO.salvar(p);
                    double comissao = p.calcularComissaoPadrao(100.0);
                    lblProf.setText(p.obterPerfil() + " | Simulação Comissão s/ R$100: R$ " + String.format("%.2f", comissao));
                } else {
                    lblProf.setText("Preencha todos os campos.");
                }
            } catch (Exception ex) {
                lblProf.setText("Erro: Verifique os dados inseridos.");
            }
        });

        layout.getChildren().addAll(new Label("--- EQUIPE DO SALÃO ---"), txtIdProf, txtNome, cbEsp, btnSalvar, lblProf);
    }

    public void configurarAbaAgendamento(VBox layout) {
        layout.setPadding(new Insets(15));
        layout.setSpacing(10);

        TextField txtCli = new TextField(); txtCli.setPromptText("Nome do Cliente");
        TextField txtProfId = new TextField(); txtProfId.setPromptText("ID do Profissional");
        TextField txtServId = new TextField(); txtServId.setPromptText("ID do Serviço");
        TextField txtData = new TextField(); txtData.setPromptText("Data/Hora (Ex: 15/10 14:00)");

        ComboBox<String> cbPag = new ComboBox<>(); cbPag.getItems().addAll("Pix", "Cartão", "Dinheiro");
        cbPag.setValue("Pix");

        Label lblStatus = new Label("SISTEMA PRONTO - AGUARDANDO AGENDAMENTO");
        lblStatus.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #0d47a1;");

        Button btnAgendar = new Button("🚀 Confirmar Agendamento");
        btnAgendar.setOnAction(e -> {
            try {
                int idProf = Integer.parseInt(txtProfId.getText());
                int idServ = Integer.parseInt(txtServId.getText());

                Profissional prof = profissionalDAO.buscarPorId(idProf);
                Servico serv = servicoDAO.buscarPorId(idServ);

                if (prof == null) {
                    lblStatus.setText("❌ ERRO: Profissional não localizado.");
                    return;
                }

                if (serv == null) {
                    lblStatus.setText("❌ ERRO: Serviço não localizado.");
                    return;
                }

                int idAgendamentoAuto = (int) (System.currentTimeMillis() % 1000000);
                agendamentoAtivo = new Agendamento(idAgendamentoAuto, txtData.getText());
                agendamentoAtivo.definirPagamento(cbPag.getValue());

                agendamentoDAO.salvar(agendamentoAtivo);

                nomeClienteAtual = txtCli.getText();
                resumoServicoAtual = serv.obterResumoServico() + " com " + prof.getNome();

                lblStatus.setText("🧾 AGENDAMENTO #" + idAgendamentoAuto + " SALVO!\n" +
                        agendamentoAtivo.confirmarAgendamento(nomeClienteAtual, resumoServicoAtual));
            } catch (Exception ex) {
                lblStatus.setText("❌ ERRO: Verifique os campos de ID.");
            }
        });

        Button btnRemarcar = new Button("📅 Remarcar Horário");
        btnRemarcar.setOnAction(e -> {
            if (agendamentoAtivo != null) {
                String novoHorario = txtData.getText();
                if (!novoHorario.isEmpty()) {
                    agendamentoAtivo.remarcar(novoHorario);
                    agendamentoDAO.atualizar(agendamentoAtivo);
                    lblStatus.setText("📅 HORÁRIO ATUALIZADO NO BANCO!\n" +
                            agendamentoAtivo.confirmarAgendamento(nomeClienteAtual, resumoServicoAtual));
                } else {
                    lblStatus.setText("❌ ERRO: Digite uma nova data/hora no campo correspondente.");
                }
            } else {
                lblStatus.setText("❌ ERRO: Nenhum agendamento ativo para remarcar.");
            }
        });

        layout.getChildren().addAll(
                new Label("--- DADOS DO CLIENTE & ATENDIMENTO ---"), txtCli, txtProfId, txtServId, txtData,
                new Separator(),
                new Label("--- OPÇÕES DE PAGAMENTO ---"), cbPag, btnAgendar, btnRemarcar,
                new Separator(), lblStatus
        );
    }
}