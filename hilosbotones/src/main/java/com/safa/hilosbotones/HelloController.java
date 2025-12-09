package com.safa.hilosbotones;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML private Button btnComenzar;
    @FXML private Button btnSuspender1, btnSuspender2;
    @FXML private Button btnReanudar1, btnReanudar2;
    @FXML private Button btnFinalizar;

    @FXML private Label lblEstado1, lblEstado2;

    @FXML private TextField txtHilo1, txtHilo2;

    private HiloContador hilo1, hilo2;

    @FXML
    public void initialize() {
        btnSuspender1.setDisable(true);
        btnSuspender2.setDisable(true);
        btnReanudar1.setDisable(true);
        btnReanudar2.setDisable(true);
        btnFinalizar.setDisable(true);

        btnComenzar.setOnAction(e -> comenzar());
        btnSuspender1.setOnAction(e -> suspender1());
        btnSuspender2.setOnAction(e -> suspender2());
        btnReanudar1.setOnAction(e -> reanudar1());
        btnReanudar2.setOnAction(e -> reanudar2());
        btnFinalizar.setOnAction(e -> finalizar());
    }

    private void comenzar() {

        hilo1 = new HiloContador("Hilo1", 500, txtHilo1);
        hilo2 = new HiloContador("Hilo2", 800, txtHilo2);

        hilo1.start();
        hilo2.start();

        btnComenzar.setDisable(true);
        btnSuspender1.setDisable(false);
        btnSuspender2.setDisable(false);
        btnFinalizar.setDisable(false);

        lblEstado1.setText("Estado: Corriendo");
        lblEstado2.setText("Estado: Corriendo");
    }

    private void suspender1() {
        hilo1.suspenderHilo();
        lblEstado1.setText("Estado: Suspendido");

        btnSuspender1.setDisable(true);
        btnReanudar1.setDisable(false);
    }

    private void suspender2() {
        hilo2.suspenderHilo();
        lblEstado2.setText("Estado: Suspendido");

        btnSuspender2.setDisable(true);
        btnReanudar2.setDisable(false);
    }

    private void reanudar1() {
        hilo1.reanudarHilo();
        lblEstado1.setText("Estado: Corriendo");

        btnReanudar1.setDisable(true);
        btnSuspender1.setDisable(false);
    }

    private void reanudar2() {
        hilo2.reanudarHilo();
        lblEstado2.setText("Estado: Corriendo");

        btnReanudar2.setDisable(true);
        btnSuspender2.setDisable(false);
    }

    public void finalizar() {
        try {
            if (hilo1 != null) { hilo1.interrupt(); hilo1.join(); }
            if (hilo2 != null) { hilo2.interrupt(); hilo2.join(); }
        } catch (InterruptedException ignored) {}

        System.out.println("Hilo1 contador final: " + (hilo1 != null ? hilo1.getContador() : 0));
        System.out.println("Hilo2 contador final: " + (hilo2 != null ? hilo2.getContador() : 0));
    }
}