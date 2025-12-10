package com.safa.granja;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlPanel extends HBox {
    public ControlPanel(Button sembrar, Button pausar, Button reiniciar) {
        this.getChildren().addAll(sembrar, pausar, reiniciar);
    }
}
