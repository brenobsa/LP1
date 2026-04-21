package Salao;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        SalaoController controller = new SalaoController();

        Tab tab1 = new Tab("Serviços");
        VBox v1 = new VBox(15); v1.setPadding(new Insets(20));
        controller.configurarAbaServico(v1);
        tab1.setContent(v1);

        Tab tab2 = new Tab("Profissionais");
        VBox v2 = new VBox(15); v2.setPadding(new Insets(20));
        controller.configurarAbaProfissional(v2);
        tab2.setContent(v2);

        Tab tab3 = new Tab("Agendamentos");
        VBox v3 = new VBox(15); v3.setPadding(new Insets(20));
        controller.configurarAbaAgendamento(v3);
        tab3.setContent(v3);

        tabPane.getTabs().addAll(tab1, tab2, tab3);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabPane, 450, 400);
        primaryStage.setTitle("Salon Manager - FATEC");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}