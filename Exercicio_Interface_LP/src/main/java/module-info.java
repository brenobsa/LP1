module org.example.Farmacia {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;


    exports Farmacia;
    opens Farmacia to javafx.fxml;
    opens Feira to javafx.graphics, javafx.fxml;
    exports Feira;
    opens Salao to javafx.graphics, javafx.fxml;
    exports Salao;
}