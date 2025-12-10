package com.safa.granja;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GranjaView extends BorderPane {
    private GridPane parcelasGrid;

    public GranjaView() {
        parcelasGrid = new GridPane();
        this.setCenter(parcelasGrid);

        // Panel de control
        Button sembrarButton = new Button("Sembrar");
        Button pausarButton = new Button("Pausar");
        Button reiniciarButton = new Button("Reiniciar");
        this.setBottom(new ControlPanel(sembrarButton, pausarButton, reiniciarButton));

        // Crear parcelas
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Parcela parcela = new Parcela();
                parcelasGrid.add(parcela, i, j);
            }
        }
    }
}
