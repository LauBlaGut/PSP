package com.safa.carrera;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HelloController {

    @FXML private GridPane pistaGrid;
    @FXML private Label estadoLabel;

    private Rectangle[] casillas = new Rectangle[70];
    private Carrera carrera;

    @FXML
    public void initialize() {
        for (int i = 0; i < 70; i++) {
            Rectangle r = new Rectangle(20, 20, Color.LIGHTGRAY);
            casillas[i] = r;
            pistaGrid.add(r, i, 0);
        }
    }

    @FXML
    public void iniciarCarrera() {
        estadoLabel.setText("¡La carrera ha comenzado!");
        carrera = new Carrera(this);
        carrera.iniciar();
    }

    public void actualizarCasillas(int posTortuga, int posLiebre) {
        Platform.runLater(() -> {
            for (int i = 0; i < 70; i++) casillas[i].setFill(Color.LIGHTGRAY);

            if (posTortuga == posLiebre) {
                casillas[posTortuga - 1].setFill(Color.RED);
            } else {
                casillas[posTortuga - 1].setFill(Color.GREEN);
                casillas[posLiebre - 1].setFill(Color.ORANGE);
            }
        });
    }

    public void mostrarGanador(String texto) {
        Platform.runLater(() -> estadoLabel.setText(texto));
    }
}