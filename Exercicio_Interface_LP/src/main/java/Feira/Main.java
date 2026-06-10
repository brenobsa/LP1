package Feira;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        FeiraController controller = new FeiraController();

        Tab tabProd = new Tab("Produtos (Estoque)");
        VBox vBoxProd = new VBox();
        controller.configurarAbaProduto(vBoxProd);
        tabProd.setContent(vBoxProd);

        Tab tabBanca = new Tab("Gestão da Banca");
        VBox vBoxBanca = new VBox();
        controller.configurarAbaBanca(vBoxBanca);
        tabBanca.setContent(vBoxBanca);

        Tab tabVenda = new Tab("Caixa / Venda");
        VBox vBoxVenda = new VBox();
        controller.configurarAbaVenda(vBoxVenda);
        tabVenda.setContent(vBoxVenda);

        tabPane.getTabs().addAll(tabProd, tabBanca, tabVenda);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabPane, 450, 500);
        primaryStage.setTitle("Sistema Integrado de Feira Livre");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}