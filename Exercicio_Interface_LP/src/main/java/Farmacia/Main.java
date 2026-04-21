package Farmacia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        FarmaciaController controller = new FarmaciaController();

        // Criando as Abas
        Tab tabMed = new Tab("Medicamentos");
        VBox vBoxMed = new VBox(10);
        controller.configurarAbaMedicamento(vBoxMed);
        tabMed.setContent(vBoxMed);

        Tab tabCli = new Tab("Clientes");
        VBox vBoxCli = new VBox(10);
        controller.configurarAbaCliente(vBoxCli);
        tabCli.setContent(vBoxCli);

        Tab tabPed = new Tab("Pedidos");
        VBox vBoxPed = new VBox(10);
        controller.configurarAbaPedido(vBoxPed);
        tabPed.setContent(vBoxPed);

        tabPane.getTabs().addAll(tabMed, tabCli, tabPed);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabPane, 400, 350);
        primaryStage.setTitle("Farmácia");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}