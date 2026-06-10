package Feira;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class FeiraController {

    private ProdutoFeiraDAO produtoDAO = new ProdutoFeiraDAO();
    private BancaDAO bancaDAO = new BancaDAO();
    private VendaFeiraDAO vendaDAO = new VendaFeiraDAO();
    private VendaFeira vendaAtual = null;

    public void configurarAbaProduto(VBox layout) {
        layout.setPadding(new Insets(15));
        layout.setSpacing(8);

        TextField txtId = new TextField(); txtId.setPromptText("ID do Produto (Numérico)");
        TextField txtNome = new TextField(); txtNome.setPromptText("Nome do Produto");
        TextField txtPreco = new TextField(); txtPreco.setPromptText("Preço por Quilo (R$)");
        Label lblStatus = new Label("Status: Aguardando");

        Button btnSalvar = new Button("Cadastrar / Atualizar");
        btnSalvar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nome = txtNome.getText();
                double preco = Double.parseDouble(txtPreco.getText());

                ProdutoFeira p = new ProdutoFeira(id, nome, preco);

                if (p.verificarDisponibilidade()) {
                    ProdutoFeira existente = produtoDAO.buscarPorId(p.id);
                    if (existente == null) {
                        produtoDAO.salvar(p);
                        lblStatus.setText("Produto cadastrado com sucesso!");
                    } else {
                        produtoDAO.atualizar(p);
                        lblStatus.setText("Preço do produto atualizado no estoque.");
                    }
                } else {
                    lblStatus.setText("Erro: Preço deve ser maior que zero.");
                }
            } catch (Exception ex) {
                lblStatus.setText("Erro: Verifique os valores inseridos.");
            }
        });

        Button btnXepa = new Button("Aplicar Desconto Xepa (20%)");
        btnXepa.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                ProdutoFeira p = produtoDAO.buscarPorId(id);
                if (p != null) {
                    p.aplicarPrecoDesconto(20);
                    produtoDAO.atualizar(p);
                    lblStatus.setText("Desconto aplicado! Novo preço: R$ " + String.format("%.2f", p.precoPorQuilo));
                } else {
                    lblStatus.setText("Produto não localizado.");
                }
            } catch (Exception ex) {
                lblStatus.setText("Digite um ID válido.");
            }
        });

        layout.getChildren().addAll(new Label("--- CADASTRO DE PRODUTOS ---"), txtId, txtNome, txtPreco, btnSalvar, btnXepa, lblStatus);
    }

    public void configurarAbaBanca(VBox layout) {
        layout.setPadding(new Insets(15));
        layout.setSpacing(8);

        TextField txtIdBanca = new TextField(); txtIdBanca.setPromptText("ID da Banca (Numérico)");
        TextField txtNomeBanca = new TextField(); txtNomeBanca.setPromptText("Nome da Banca");
        ComboBox<String> cbSetor = new ComboBox<>();
        cbSetor.getItems().addAll("Frutas", "Verduras", "Pastel", "Peixes");
        cbSetor.setPromptText("Selecione o Setor");
        Label lblStatus = new Label("Status: Aguardando");

        Button btnCadastrar = new Button("Registrar Banca");
        btnCadastrar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtIdBanca.getText());
                String nome = txtNomeBanca.getText();
                String setor = cbSetor.getValue();

                Banca b = new Banca(id, nome, setor);

                if (b.validarId() && setor != null && !nome.isEmpty()) {
                    try {
                        bancaDAO.salvar(b);
                        lblStatus.setText(b.gerarAnuncio());
                    } catch (RuntimeException ex) {
                        lblStatus.setText("Erro: Código de ID já utilizado.");
                    }
                } else {
                    lblStatus.setText("Erro: Preencha todos os campos corretamente.");
                }
            } catch (Exception ex) {
                lblStatus.setText("Erro: ID deve ser numérico.");
            }
        });

        layout.getChildren().addAll(new Label("--- GESTÃO DE BANCAS ---"), txtIdBanca, txtNomeBanca, cbSetor, btnCadastrar, lblStatus);
    }

    public void configurarAbaVenda(VBox layout) {
        layout.setPadding(new Insets(15));
        layout.setSpacing(10);

        TextField txtBancaId = new TextField(); txtBancaId.setPromptText("ID da Banca Operadora");
        TextField txtProdId = new TextField(); txtProdId.setPromptText("ID do Produto (Bipagem)");
        TextField txtPeso = new TextField(); txtPeso.setPromptText("Peso em Kg (Ex: 1.5)");

        ComboBox<String> cbPag = new ComboBox<>();
        cbPag.getItems().addAll("Dinheiro", "Pix", "Cartão");
        cbPag.setValue("Dinheiro");

        Label lblCaixa = new Label("CAIXA DISPONÍVEL");
        lblCaixa.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #2e7d32;");

        Button btnBipar = new Button("⚡ Bipar Item e Lançar Peso");
        btnBipar.setOnAction(e -> {
            try {
                int idBanca = Integer.parseInt(txtBancaId.getText());
                int idProd = Integer.parseInt(txtProdId.getText());
                double peso = Double.parseDouble(txtPeso.getText());

                Banca banca = bancaDAO.buscarPorId(idBanca);
                ProdutoFeira prod = produtoDAO.buscarPorId(idProd);

                if (banca == null) {
                    lblCaixa.setText("❌ ERRO: Banca operacional não cadastrada.");
                    return;
                }

                if (prod == null) {
                    lblCaixa.setText("❌ ERRO: Produto não localizado.");
                    return;
                }

                int idVendaAutomatico = (int) (System.currentTimeMillis() % 1000000);
                double valorCalculado = prod.calcularPrecoPorPeso(peso);

                vendaAtual = new VendaFeira(idVendaAutomatico, valorCalculado, banca.id);
                vendaDAO.salvar(vendaAtual);

                lblCaixa.setText("🧾 " + vendaAtual.adicionarItem(prod.nome) + " GERADO!\n" +
                        "🎪 Origem: " + banca.getNome() + "\n" +
                        "💰 Total Parcial: R$ " + String.format("%.2f", vendaAtual.valorVenda));
            } catch (Exception ex) {
                lblCaixa.setText("❌ ERRO: Verifique as informações digitadas.");
            }
        });

        Button btnFinalizar = new Button("✅ Encerrar Cupom");
        btnFinalizar.setOnAction(e -> {
            if (vendaAtual != null) {
                lblCaixa.setText("🎉 " + vendaAtual.finalizarVenda(cbPag.getValue()));
                vendaDAO.atualizar(vendaAtual);
                vendaAtual = null;
            } else {
                lblCaixa.setText("❌ ERRO: Não há cupom ativo para fechamento.");
            }
        });

        Button btnCancelar = new Button("🛑 Cancelar Venda");
        btnCancelar.setOnAction(e -> {
            if (vendaAtual != null) {
                vendaAtual.cancelarVenda();
                vendaDAO.atualizar(vendaAtual);
                lblCaixa.setText("🛑 Operação abortada. Caixa limpo.");
                vendaAtual = null;
            } else {
                lblCaixa.setText("❌ ERRO: O caixa já está limpo.");
            }
        });

        layout.getChildren().addAll(
                new Label("--- IDENTIFICAÇÃO DA BANCA ---"), txtBancaId,
                new Separator(),
                new Label("--- ENTRADA DE PESAGEM ---"), txtProdId, txtPeso, btnBipar,
                new Separator(),
                new Label("--- PAGAMENTO & CONCLUSÃO ---"), cbPag, btnFinalizar, btnCancelar,
                new Separator(), lblCaixa
        );
    }
}