package org.example.granja;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HiloCultivo extends Thread {
    private Rectangle visual;
    private ControlParcela control;
    private int progreso = 0; // 0 a 100%

    public HiloCultivo(Rectangle visual, ControlParcela control) {
        this.visual = visual;
        this.control = control;
    }

    @Override
    public void run() {
        try {
            while (progreso < 100) {
                // Sincronización: Esperar agua si es necesario
                control.intentarCrecer();

                progreso += 10;

                // Actualizar UI según el progreso
                Platform.runLater(() -> {
                    if (progreso < 50) visual.setFill(Color.BROWN);
                    else if (progreso < 100) visual.setFill(Color.GREEN);
                    else visual.setFill(Color.GOLD); // Listo para cosechar
                });

                Thread.sleep(1000); // Velocidad de crecimiento
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}