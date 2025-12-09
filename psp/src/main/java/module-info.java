module com.safa.psp {
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

    opens com.safa.psp to javafx.fxml;
    exports com.safa.psp;
    exports com.safa.psp.controlador;
    opens com.safa.psp.controlador to javafx.fxml;
    exports com.safa.psp.Alberti;
    opens com.safa.psp.Alberti to javafx.fxml;
}