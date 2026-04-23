package Feira;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class FeiraController {
    private List<ProdutoFeira> estoque = new ArrayList<>();
    private List<Banca> bancas = new ArrayList<>();
    private VendaFeira vendaAtual = new VendaFeira(101);

    public void configurarAbaProduto(VBox layout) {
        TextField txtProd = new TextField(); txtProd.setPromptText("Nome do Produto");
        TextField txtPreco = new TextField(); txtPreco.setPromptText("Preço p/ Kg");
        Label lblRes = new Label("Aguardando...");

        Button btnAdd = new Button("Cadastrar Produto");
        btnAdd.setOnAction(e -> {
            try {
                estoque.add(new ProdutoFeira(txtProd.getText(), Double.parseDouble(txtPreco.getText())));
                lblRes.setText("Sucesso: " + txtProd.getText() + " cadastrado!");
            } catch (Exception ex) { lblRes.setText("Erro: Preço inválido."); }
        });

        Button btnXepa = new Button("Aplicar Desconto (20%)");
        btnXepa.setOnAction(e -> {
            estoque.stream().filter(p -> p.getNome().equalsIgnoreCase(txtProd.getText())).findFirst()
                    .ifPresentOrElse(p -> {
                        if (!p.isDescontoAplicado()) {
                            p.aplicarPrecoDesconto(20);
                            lblRes.setText("Desconto aplicado! Novo preço: R$ " + String.format("%.2f", p.getPrecoPorQuilo()));
                        } else { lblRes.setText("Erro: Desconto já utilizado para este item."); }
                    }, () -> lblRes.setText("Produto não encontrado."));
        });

        layout.getChildren().addAll(new Label("Estoque"), txtProd, txtPreco, btnAdd, btnXepa, lblRes);
    }

    public void configurarAbaBanca(VBox layout) {
        TextField txtBanca = new TextField(); txtBanca.setPromptText("Nome da Banca");
        ComboBox<String> cbSetor = new ComboBox<>();
        cbSetor.getItems().addAll("Frutas", "Verduras", "Pastel", "Peixes");
        Label lblAnuncio = new Label("Banca Offline.");

        Button btnAnunciar = new Button("Publicar e Abrir");
        btnAnunciar.setOnAction(e -> {
            if (cbSetor.getValue() != null && !txtBanca.getText().isEmpty()) {
                Banca b = new Banca(txtBanca.getText(), cbSetor.getValue());
                b.alternarStatusBanca();
                bancas.add(b);
                lblAnuncio.setText(b.gerarAnuncioChamada());
            } else { lblAnuncio.setText("Erro: Preencha nome e setor."); }
        });

        layout.getChildren().addAll(new Label("Gestão de Banca"), txtBanca, cbSetor, btnAnunciar, lblAnuncio);
    }

    public void configurarAbaVenda(VBox layout) {
        TextField txtValor = new TextField(); txtValor.setPromptText("Valor R$");
        ComboBox<String> cbPag = new ComboBox<>();
        cbPag.getItems().addAll("Dinheiro", "Pix", "Cartão");
        cbPag.setValue("Dinheiro");
        Label lblCaixa = new Label("Total: R$ 0,00");

        Button btnAdd = new Button("Registrar Item");
        btnAdd.setOnAction(e -> {
            try {
                vendaAtual.registrarItem(Double.parseDouble(txtValor.getText()));
                lblCaixa.setText("Subtotal: R$ " + String.format("%.2f", vendaAtual.getValorVenda()));
                txtValor.clear();
            } catch (Exception ex) { lblCaixa.setText("Valor inválido."); }
        });

        Button btnFinalizar = new Button("Encerrar Venda");
        btnFinalizar.setOnAction(e -> {
            vendaAtual.definirFormaPagamento(cbPag.getValue());
            lblCaixa.setText(vendaAtual.finalizarVenda());
            vendaAtual = new VendaFeira((int)(Math.random() * 500));
        });

        layout.getChildren().addAll(new Label("Frente de Caixa"), txtValor, btnAdd, cbPag, btnFinalizar, lblCaixa);
    }
}