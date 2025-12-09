package com.safa.hilosbotones;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/safa/hilosbotones/vista.fxml")
        );

        Scene scene = new Scene(loader.load());
        stage.setTitle("Control de Hilos — Suspender / Reanudar");
        stage.setScene(scene);
        stage.show();

        // finalización al cerrar ventana
        HelloController controller = loader.getController();
        stage.setOnCloseRequest(e -> controller.finalizar());
    }

    public static void main(String[] args) {
        launch();
    }
}