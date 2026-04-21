package Feira;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class FeiraController {

    public void configurarAbaProduto(VBox layout) {
        TextField txtProd = new TextField();
        txtProd.setPromptText("Ex: Banana");
        TextField txtPreco = new TextField();
        txtPreco.setPromptText("Preço p/ Kg");
        Label lblRes = new Label();

        Button btnXepa = new Button("Aplicar Desconto (20%)");
        btnXepa.setOnAction(e -> {
            ProdutoFeira p = new ProdutoFeira(txtProd.getText(), Double.parseDouble(txtPreco.getText()), 50);
            p.aplicarPrecoDesconto(20);
            lblRes.setText("Preço Desconto: R$ " + p.getPrecoPorQuilo());
        });

        layout.getChildren().addAll(new Label("Produtos da Feira"), txtProd, txtPreco, btnXepa, lblRes);
    }

    public void configurarAbaBanca(VBox layout) {
        TextField txtBanca = new TextField();
        txtBanca.setPromptText("Nome da Banca");
        ComboBox<String> cbSetor = new ComboBox<>();
        cbSetor.getItems().addAll("Frutas", "Verduras", "Pastel", "Peixes");
        Label lblAnuncio = new Label();

        Button btnGritar = new Button("Anunciar Oferta!");
        btnGritar.setOnAction(e -> {
            Banca b = new Banca(txtBanca.getText(), cbSetor.getValue());
            lblAnuncio.setText(b.gerarAnuncioChamada());
        });

        layout.getChildren().addAll(new Label("Gestão da Banca"), txtBanca, cbSetor, btnGritar, lblAnuncio);
    }

    public void configurarAbaVenda(VBox layout) {
        TextField txtValor = new TextField();
        txtValor.setPromptText("Valor do Item (R$)");

        Label lblInstrucao = new Label("Forma de Pagamento:");
        ComboBox<String> cbPagamento = new ComboBox<>();
        cbPagamento.getItems().addAll("Dinheiro", "Pix", "Cartão Débito", "Cartão Crédito");
        cbPagamento.setValue("Dinheiro"); // Valor inicial

        Label lblStatusVenda = new Label("Total: R$ 0,00");
        VendaFeira vendaAtual = new VendaFeira(123);

        Button btnAdd = new Button("Adicionar Item");
        btnAdd.setOnAction(e -> {
            try {
                double valor = Double.parseDouble(txtValor.getText());
                vendaAtual.registrarItem(valor);
                lblStatusVenda.setText("Total Parcial: R$ " + String.format("%.2f", vendaAtual.getValorVenda()));
                txtValor.clear();
            } catch (Exception ex) {
                lblStatusVenda.setText("Erro: Digite um valor válido!");
            }
        });

        Button btnFinalizar = new Button("Finalizar Venda");
        btnFinalizar.setOnAction(e -> {
            // Aplica a forma de pagamento selecionada no objeto
            vendaAtual.definirFormaPagamento(cbPagamento.getValue());
            lblStatusVenda.setText(vendaAtual.finalizarVenda());
        });

        layout.getChildren().addAll(
                new Label("Caixa da Feira"),
                txtValor,
                btnAdd,
                new Separator(),
                lblInstrucao,
                cbPagamento,
                btnFinalizar,
                lblStatusVenda
        );
    }
}
