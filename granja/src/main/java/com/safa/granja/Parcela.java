package com.safa.granja;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Parcela extends Rectangle {
    private boolean vacia = true;
    private boolean enCrecimiento = false;
    private boolean riegoActivo = false;

    public Parcela() {
        super(50, 50, Color.BROWN); // Default size and color for an empty parcel
    }

    public boolean isVacia() {
        return vacia;
    }

    public void sembrar() {
        if (vacia) {
            vacia = false;
            enCrecimiento = true;
            this.setFill(Color.GREEN); // Change color to indicate active growth
        }
    }

    public void pausarCrecimiento() {
        if (enCrecimiento) {
            enCrecimiento = false;
        }
    }

    public void activarRiego() {
        if (!vacia) {
            riegoActivo = true;
            this.setStroke(Color.BLUE); // Add a blue border to indicate irrigation
        }
    }

    public void desactivarRiego() {
        if (riegoActivo) {
            riegoActivo = false;
            this.setStroke(null); // Remove the border
        }
    }

    public void actualizarEstado(Color color) {
        this.setFill(color); // Update the parcel's color to reflect its state
    }
}