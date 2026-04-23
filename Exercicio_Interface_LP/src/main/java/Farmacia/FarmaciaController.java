package Farmacia;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class FarmaciaController {

    private List<Medicamento> estoque = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private Pedido pedidoAtual = null;

    public void configurarAbaMedicamento(VBox layout) {
        TextField txtNome = new TextField(); txtNome.setPromptText("Nome");
        TextField txtPreco = new TextField(); txtPreco.setPromptText("Preço");
        TextField txtQtd = new TextField(); txtQtd.setPromptText("Quantidade");
        Label lblStatus = new Label("Status: Aguardando");

        Button btnSalvar = new Button("Salvar / Repor");
        btnSalvar.setOnAction(e -> {
            try {
                String nome = txtNome.getText();
                double preco = Double.parseDouble(txtPreco.getText());
                int qtd = Integer.parseInt(txtQtd.getText());

                Medicamento m = estoque.stream()
                        .filter(med -> med.getNomeGenerico().equalsIgnoreCase(nome))
                        .findFirst().orElse(null);

                if (m == null) {
                    m = new Medicamento(nome, preco, qtd);
                    estoque.add(m);
                    lblStatus.setText("Cadastrado. Disponível: " + m.verificarDisponibilidade());
                } else {
                    m.solicitarReposicao(qtd);
                    lblStatus.setText("Estoque atualizado: " + m.getQuantidadeEstoque());
                }
            } catch (Exception ex) { lblStatus.setText("Erro nos valores."); }
        });

        Button btnDesconto = new Button("Aplicar 10% Desconto");
        btnDesconto.setOnAction(e -> {
            estoque.stream()
                    .filter(med -> med.getNomeGenerico().equalsIgnoreCase(txtNome.getText()))
                    .findFirst().ifPresentOrElse(m -> {
                        m.aplicarDesconto(10);
                        lblStatus.setText("Novo preço: R$ " + String.format("%.2f", m.getPreco()));
                    }, () -> lblStatus.setText("Medicamento não encontrado."));
        });

        layout.getChildren().addAll(new Label("Medicamentos"), txtNome, txtPreco, txtQtd, btnSalvar, btnDesconto, lblStatus);
    }

    public void configurarAbaCliente(VBox layout) {
        TextField txtNome = new TextField(); txtNome.setPromptText("Nome");
        TextField txtCpf = new TextField(); txtCpf.setPromptText("CPF (Apenas 11 números)");
        TextField txtEmail = new TextField(); txtEmail.setPromptText("Email");
        Label lblStatus = new Label("Status: Aguardando");

        // Limitador de 11 dígitos e apenas números para o CPF
        txtCpf.setTextFormatter(new TextFormatter<>(change -> {
            String novoTexto = change.getControlNewText();
            if (novoTexto.matches("\\d*") && novoTexto.length() <= 11) {
                return change;
            }
            return null;
        }));

        Button btnCadastrar = new Button("Validar e Cadastrar");
        btnCadastrar.setOnAction(e -> {
            Cliente c = new Cliente(txtCpf.getText(), txtNome.getText(), txtEmail.getText());
            if (c.validarDadosCadastrais()) {
                clientes.add(c);
                lblStatus.setText("Cliente salvo com sucesso.");
            } else {
                lblStatus.setText("Erro: CPF deve ter 11 dígitos e Email conter @");
            }
        });

        Button btnHistorico = new Button("Consultar Histórico");
        btnHistorico.setOnAction(e -> {
            clientes.stream()
                    .filter(cl -> cl.getNome().equalsIgnoreCase(txtNome.getText()))
                    .findFirst().ifPresentOrElse(
                            cl -> lblStatus.setText(cl.consultarHistoricoCompras()),
                            () -> lblStatus.setText("Cliente não encontrado.")
                    );
        });

        layout.getChildren().addAll(new Label("Clientes"), txtNome, txtCpf, txtEmail, btnCadastrar, btnHistorico, lblStatus);
    }

    public void configurarAbaPedido(VBox layout) {
        TextField txtNum = new TextField(); txtNum.setPromptText("Nº Pedido");
        TextField txtItem = new TextField(); txtItem.setPromptText("Nome do Item");
        Label lblStatus = new Label("Status: Sem pedido");

        Button btnCriar = new Button("Adicionar Item ao Pedido");
        btnCriar.setOnAction(e -> {
            try {
                pedidoAtual = new Pedido(Integer.parseInt(txtNum.getText()), 150.0);
                lblStatus.setText(pedidoAtual.adicionarItem(txtItem.getText()));
            } catch (Exception ex) { lblStatus.setText("Erro no número do pedido."); }
        });

        Button btnPagar = new Button("Finalizar Pagamento");
        btnPagar.setOnAction(e -> {
            if (pedidoAtual != null) {
                lblStatus.setText(pedidoAtual.finalizarPagamento("Cartão/Pix"));
            } else { lblStatus.setText("Crie um pedido primeiro."); }
        });

        Button btnCancelar = new Button("Cancelar Pedido");
        btnCancelar.setOnAction(e -> {
            if (pedidoAtual != null) {
                pedidoAtual.cancelarPedido();
                lblStatus.setText("Pedido cancelado.");
            }
        });

        layout.getChildren().addAll(new Label("Vendas"), txtNum, txtItem, btnCriar, btnPagar, btnCancelar, lblStatus);
    }
}