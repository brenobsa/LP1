package Salao;

import javafx.application.Application;
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
        VBox v1 = new VBox();
        controller.configurarAbaServico(v1);
        tab1.setContent(v1);

        Tab tab2 = new Tab("Profissionais");
        VBox v2 = new VBox();
        controller.configurarAbaProfissional(v2);
        tab2.setContent(v2);

        Tab tab3 = new Tab("Agendamentos");
        VBox v3 = new VBox();
        controller.configurarAbaAgendamento(v3);
        tab3.setContent(v3);

        tabPane.getTabs().addAll(tab1, tab2, tab3);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabPane, 450, 520);
        primaryStage.setTitle("Gestão do Salão de Beleza");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}