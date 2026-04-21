package Farmacia;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class FarmaciaController {

    // Métodos para a Aba de Medicamento
    public void configurarAbaMedicamento(VBox layout) {
        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome do Medicamento");
        TextField txtPreco = new TextField();
        txtPreco.setPromptText("Preço");
        Label lblStatus = new Label();

        Button btnDesconto = new Button("Aplicar 10% Desconto");
        btnDesconto.setOnAction(e -> {
            try {
                Medicamento m = new Medicamento(txtNome.getText(), Double.parseDouble(txtPreco.getText()), 10);
                m.aplicarDesconto(10);
                lblStatus.setText("Preço com desconto: R$ " + m.getPreco());
            } catch (Exception ex) { lblStatus.setText("Erro: Insira valores válidos."); }
        });

        layout.getChildren().addAll(new Label("Gerenciar Estoque"), txtNome, txtPreco, btnDesconto, lblStatus);
    }

    // Métodos para a Aba de Cliente
    public void configurarAbaCliente(VBox layout) {
        TextField txtNomeCli = new TextField();
        txtNomeCli.setPromptText("Nome do Cliente");
        TextField txtCpf = new TextField();
        txtCpf.setPromptText("CPF (11 dígitos)");
        Label lblStatus = new Label();

        Button btnValidar = new Button("Validar Cadastro");
        btnValidar.setOnAction(e -> {
            Cliente c = new Cliente(txtCpf.getText(), txtNomeCli.getText(), "email@teste.com");
            if(c.validarDadosCadastrais()) {
                lblStatus.setText("Cliente validado com sucesso!");
            } else {
                lblStatus.setText("CPF Inválido!");
            }
        });

        layout.getChildren().addAll(new Label("Cadastro de Clientes"), txtNomeCli, txtCpf, btnValidar, lblStatus);
    }

    // Métodos para a Aba de Pedido
    public void configurarAbaPedido(VBox layout) {
        TextField txtNumPedido = new TextField();
        txtNumPedido.setPromptText("Número do Pedido");
        Label lblStatus = new Label();

        Button btnPagar = new Button("Finalizar Venda");
        btnPagar.setOnAction(e -> {
            Pedido p = new Pedido(Integer.parseInt(txtNumPedido.getText()), "21/04/2026", 150.0);
            lblStatus.setText(p.finalizarPagamento("Pix"));
        });

        layout.getChildren().addAll(new Label("Finalizar Venda"), txtNumPedido, btnPagar, lblStatus);
    }
}
