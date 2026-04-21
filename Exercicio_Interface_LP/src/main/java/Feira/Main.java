package Feira;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Inicializa o TabPane (Painel de Abas)
        TabPane tabPane = new TabPane();

        // Instancia o controlador da Feira
        FeiraController controller = new FeiraController();

        // --- ABA 1: PRODUTOS ---
        Tab tabProd = new Tab("Produtos (Estoque)");
        VBox vBoxProd = new VBox(15); // Espaçamento de 15px entre elementos
        vBoxProd.setPadding(new Insets(20)); // Margem interna
        controller.configurarAbaProduto(vBoxProd);
        tabProd.setContent(vBoxProd);

        // --- ABA 2: BANCA ---
        Tab tabBanca = new Tab("Gestão da Banca");
        VBox vBoxBanca = new VBox(15);
        vBoxBanca.setPadding(new Insets(20));
        controller.configurarAbaBanca(vBoxBanca);
        tabBanca.setContent(vBoxBanca);

        // --- ABA 3: VENDAS ---
        Tab tabVenda = new Tab("Caixa / Venda");
        VBox vBoxVenda = new VBox(15);
        vBoxVenda.setPadding(new Insets(20));
        controller.configurarAbaVenda(vBoxVenda);
        tabVenda.setContent(vBoxVenda);

        // Adiciona as abas ao painel principal
        tabPane.getTabs().addAll(tabProd, tabBanca, tabVenda);

        // Impede que o usuário feche as abas (opcional)
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Configuração da Janela (Scene)
        Scene scene = new Scene(tabPane, 450, 400);

        primaryStage.setTitle("Feira");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Ponto de entrada que inicia a aplicação JavaFX
        launch(args);
    }
}