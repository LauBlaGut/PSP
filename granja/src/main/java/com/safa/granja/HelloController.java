package com.safa.granja;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class HelloController {

    @FXML
    private GridPane parcelasGrid;

    @FXML
    private Label estadoLabel;

    @FXML
    private Slider velocidadSlider;

    private boolean aspersoresActivos = false;

    @FXML
    protected void onSembrar() {
        estadoLabel.setText("Estado: Sembrando...");
        parcelasGrid.getChildren().forEach(node -> {
            if (node instanceof Parcela) {
                Parcela parcela = (Parcela) node;
                if (parcela.isVacia()) {
                    parcela.sembrar();
                    Cultivo cultivo = new Cultivo(parcela);
                    new Thread(cultivo).start();
                }
            }
        });
    }

    @FXML
    protected void onPausar() {
        estadoLabel.setText("Estado: Pausado");
        parcelasGrid.getChildren().forEach(node -> {
            if (node instanceof Parcela) {
                ((Parcela) node).pausarCrecimiento();
            }
        });
    }

    @FXML
    protected void onReiniciar() {
        estadoLabel.setText("Estado: Reiniciando...");
        parcelasGrid.getChildren().clear();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Parcela parcela = new Parcela();
                parcelasGrid.add(parcela, i, j);
            }
        }
    }

    @FXML
    protected void onActivarAspersores() {
        if (!aspersoresActivos) {
            aspersoresActivos = true;
            estadoLabel.setText("Estado: Aspersores activados");
            parcelasGrid.getChildren().forEach(node -> {
                if (node instanceof Parcela) {
                    ((Parcela) node).activarRiego();
                }
            });
        }
    }

    @FXML
    protected void onDesactivarAspersores() {
        if (aspersoresActivos) {
            aspersoresActivos = false;
            estadoLabel.setText("Estado: Aspersores desactivados");
            parcelasGrid.getChildren().forEach(node -> {
                if (node instanceof Parcela) {
                    ((Parcela) node).desactivarRiego();
                }
            });
        }
    }
}