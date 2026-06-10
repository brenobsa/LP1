package Farmacia;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.List;

public class FarmaciaController {

    private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private Pedido pedidoAtual = null;

    public void configurarAbaMedicamento(VBox layout) {
        layout.setPadding(new Insets(15));
        layout.setSpacing(8);

        TextField txtId = new TextField(); txtId.setPromptText("ID do Produto (Numérico)");
        TextField txtNome = new TextField(); txtNome.setPromptText("Nome do Medicamento");
        TextField txtPreco = new TextField(); txtPreco.setPromptText("Preço (R$)");
        Label lblStatus = new Label("Status: Aguardando");

        Button btnSalvar = new Button("Cadastrar / Atualizar");
        btnSalvar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nome = txtNome.getText();
                double preco = Double.parseDouble(txtPreco.getText());

                Medicamento m = new Medicamento(id, nome, preco);

                if (m.validarId()) {
                    Medicamento existente = medicamentoDAO.buscarPorId(m.id);
                    if (existente == null) {
                        medicamentoDAO.salvar(m);
                        lblStatus.setText("Cadastrado. Disponível: " + m.verificarDisponibilidade());
                    } else {
                        existente.nome = m.nome;
                        existente.preco = m.preco;
                        medicamentoDAO.actualizar(existente);
                        lblStatus.setText("Medicamento atualizado com sucesso. Ativo: " + existente.verificarDisponibilidade());
                    }
                } else {
                    lblStatus.setText("Erro: ID inválido (Deve ser maior que 0).");
                }
            } catch (Exception ex) {
                lblStatus.setText("Erro nos valores ou banco.");
            }
        });

        Button btnDesconto = new Button("Aplicar 10% Desconto");
        btnDesconto.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Medicamento m = medicamentoDAO.buscarPorId(id);
                if (m != null && m.validarId()) {
                    m.aplicarDesconto(10);
                    medicamentoDAO.actualizar(m);
                    lblStatus.setText("Novo preço salvo: R$ " + String.format("%.2f", m.preco));
                } else {
                    lblStatus.setText("Medicamento não encontrado ou ID inválido.");
                }
            } catch (Exception ex) {
                lblStatus.setText("Digite um ID válido.");
            }
        });

        layout.getChildren().addAll(new Label("--- CADASTRO DE PRODUTOS ---"), txtId, txtNome, txtPreco, btnSalvar, btnDesconto, lblStatus);
    }

    public void configurarAbaCliente(VBox layout) {
        layout.setPadding(new Insets(15));
        layout.setSpacing(8);

        TextField txtNome = new TextField(); txtNome.setPromptText("Nome do Cliente");
        TextField txtCpf = new TextField(); txtCpf.setPromptText("CPF (Apenas 11 números)");
        TextField txtTelefone = new TextField(); txtTelefone.setPromptText("Telefone (Apenas 9 números)");
        Label lblStatus = new Label("Status: Aguardando");

        txtCpf.setTextFormatter(new TextFormatter<>(change -> {
            String novoTexto = change.getControlNewText();
            if (novoTexto.matches("\\d*") && novoTexto.length() <= 11) return change;
            return null;
        }));

        txtTelefone.setTextFormatter(new TextFormatter<>(change -> {
            String novoTexto = change.getControlNewText();
            if (novoTexto.matches("\\d*") && novoTexto.length() <= 9) return change;
            return null;
        }));

        Button btnCadastrar = new Button("Salvar Cliente");
        btnCadastrar.setOnAction(e -> {
            Cliente c = new Cliente(txtCpf.getText(), txtNome.getText(), txtTelefone.getText());
            if (c.validarDadosCadastrais()) {
                try {
                    clienteDAO.salvar(c);
                    lblStatus.setText("Cliente " + c.getNome() + " salvo com sucesso.");
                } catch (RuntimeException ex) {
                    if (ex.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
                        lblStatus.setText("Erro: Este CPF já está cadastrado!");
                    } else {
                        lblStatus.setText("Erro ao salvar no banco.");
                    }
                }
            } else {
                lblStatus.setText("Erro: CPF deve ter 11 dígitos e Telefone 9 dígitos.");
            }
        });

        Button btnHistorico = new Button("📄 Ver Histórico de Compras");
        btnHistorico.setOnAction(e -> {
            String cpf = txtCpf.getText();
            Cliente c = clienteDAO.buscarPorCpf(cpf);
            if (c != null) {
                List<String> compras = clienteDAO.buscarPedidosDoCliente(cpf);
                if (compras.isEmpty()) {
                    lblStatus.setText(c.consultarHistoricoCompras() + "\nNenhuma compra registrada.");
                } else {
                    StringBuilder sb = new StringBuilder(c.consultarHistoricoCompras() + "\n");
                    for (String comp : compras) {
                        sb.append(comp).append("\n");
                    }
                    lblStatus.setText(sb.toString());
                }
            } else {
                lblStatus.setText("Erro: Digite um CPF cadastrado para ver o histórico.");
            }
        });

        layout.getChildren().addAll(new Label("--- CADASTRO DE CLIENTES ---"), txtNome, txtCpf, txtTelefone, btnCadastrar, btnHistorico, lblStatus);
    }

    public void configurarAbaPedido(VBox layout) {
        layout.setPadding(new Insets(15));
        layout.setSpacing(10);

        TextField txtClienteCpf = new TextField(); txtClienteCpf.setPromptText("CPF do Cliente (11 dígitos)");
        TextField txtIdMedicamento = new TextField(); txtIdMedicamento.setPromptText("Código/ID do Medicamento (Bipagem)");

        Label lblStatus = new Label("SISTEMA PRONTO - AGUARDANDO PRODUTO");
        lblStatus.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #0d47a1;");

        txtClienteCpf.setTextFormatter(new TextFormatter<>(change -> {
            String novoTexto = change.getControlNewText();
            if (novoTexto.matches("\\d*") && novoTexto.length() <= 11) return change;
            return null;
        }));

        Button btnBipar = new Button("⚡ Bipar Produto e Gerar Cupom");
        btnBipar.setOnAction(e -> {
            try {
                int idMed = Integer.parseInt(txtIdMedicamento.getText());
                String cpfCli = txtClienteCpf.getText();

                Cliente cl = clienteDAO.buscarPorCpf(cpfCli);
                Medicamento med = medicamentoDAO.buscarPorId(idMed);

                if (cl == null) {
                    lblStatus.setText("❌ ERRO: CPF do cliente não encontrado.");
                    return;
                }

                if (med == null) {
                    lblStatus.setText("❌ ERRO: Código de produto inválido.");
                    return;
                }

                if (med.verificarDisponibilidade() && med.validarId()) {
                    int numeroPedidoAutomatico = (int) (System.currentTimeMillis() % 1000000);

                    pedidoAtual = new Pedido(numeroPedidoAutomatico, med.preco);
                    pedidoAtual.clienteCpf = cl.cpf;

                    pedidoDAO.salvar(pedidoAtual);

                    lblStatus.setText("🧾 " + pedidoAtual.adicionarItem(String.valueOf(med.id)) + " GERADO!\n" +
                            "📦 Item: " + med.nome + " | R$ " + med.preco + "\n" +
                            "👤 Cliente: " + cl.getNome());
                } else {
                    lblStatus.setText("❌ ERRO: Produto sem preço ativo ou ID inválido.");
                }
            } catch (Exception ex) {
                lblStatus.setText("❌ ERRO: Verifique os campos numéricos.");
            }
        });

        Button btnPagar = new Button("✅ Concluir e Finalizar Venda");
        btnPagar.setOnAction(e -> {
            if (pedidoAtual != null) {
                lblStatus.setText("🎉 Venda Concluída! " + pedidoAtual.finalizarPagamento("Dinheiro/Cartão"));
                pedidoDAO.atualizar(pedidoAtual);
                pedidoAtual = null;
            } else {
                lblStatus.setText("❌ ERRO: Não há nenhum cupom em aberto no momento.");
            }
        });

        Button btnCancelar = new Button("🛑 Cancelar Cupom Atual");
        btnCancelar.setOnAction(e -> {
            if (pedidoAtual != null) {
                pedidoAtual.cancelarPedido();
                pedidoDAO.atualizar(pedidoAtual);
                lblStatus.setText("🛑 Cupom #" + pedidoAtual.numero + " foi zerado e cancelado no banco.");
                pedidoAtual = null;
            } else {
                lblStatus.setText("❌ ERRO: O caixa já está vazio.");
            }
        });

        layout.getChildren().addAll(
                new Label("--- IDENTIFICAÇÃO DO CONSUMIDOR ---"),
                txtClienteCpf,
                new Separator(),
                new Label("--- BIPAGEM DO PRODUTO ---"),
                txtIdMedicamento, btnBipar,
                new Separator(),
                new Label("--- FINALIZAÇÃO OPERACIONAL ---"),
                btnPagar, btnCancelar,
                new Separator(),
                lblStatus
        );
    }
}