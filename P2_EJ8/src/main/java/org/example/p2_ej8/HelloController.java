package org.example.p2_ej8;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML private Button btnComenzar;
    @FXML private Button btnFinalizar;

    @FXML private Button btn_suspender1;
    @FXML private Button btn_reanudar1;
    @FXML private TextField txt1;

    @FXML private Button btn_suspender2;
    @FXML private Button btn_reanudar2;
    @FXML private TextField txt2;

    private MiHilo hilo1;
    private MiHilo hilo2;

    @FXML
    public void initialize() {
        // Comenzar proceso
        btnComenzar.setOnAction(e -> iniciarHilos());

        // Suspender y reanudar hilo 1
        btn_suspender1.setOnAction(e -> hilo1.suspenderHilo());
        btn_reanudar1.setOnAction(e -> hilo1.reanudarHilo());

        // Suspender y reanudar hilo 2
        btn_suspender2.setOnAction(e -> hilo2.suspenderHilo());
        btn_reanudar2.setOnAction(e -> hilo2.reanudarHilo());

        // Finalizar ambos hilos
        btnFinalizar.setOnAction(e -> finalizarHilos());
    }

    private void iniciarHilos() {
        hilo1 = new MiHilo("Hilo 1", 500);
        hilo2 = new MiHilo("Hilo 2", 700);

        hilo1.start();
        hilo2.start();

        btnComenzar.setDisable(true);

        // Hilo para actualizar TextFields
        Thread refresco = new Thread(() -> {
            while (hilo1.isAlive() || hilo2.isAlive()) {
                Platform.runLater(() -> {
                    if (hilo1 != null) txt1.setText(String.valueOf(hilo1.getContador()));
                    if (hilo2 != null) txt2.setText(String.valueOf(hilo2.getContador()));
                });
                try {
                    Thread.sleep(100); // refresca cada 100 ms
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        refresco.setDaemon(true);
        refresco.start();
    }

    private void finalizarHilos() {
        if (hilo1 != null) hilo1.finalizar();
        if (hilo2 != null) hilo2.finalizar();

        System.out.println("Hilo 1 final: " + (hilo1 != null ? hilo1.getContador() : 0));
        System.out.println("Hilo 2 final: " + (hilo2 != null ? hilo2.getContador() : 0));
    }
}
