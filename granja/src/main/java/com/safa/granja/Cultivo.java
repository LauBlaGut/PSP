package com.safa.granja;

import javafx.scene.paint.Color;

public class Cultivo implements Runnable {
    private Parcela parcela;
    private boolean enCrecimiento = true;

    public Cultivo(Parcela parcela) {
        this.parcela = parcela;
    }

    @Override
    public void run() {
        try {
            parcela.actualizarEstado(Color.GREEN); // Crecimiento activo
            Thread.sleep(5000); // Simular tiempo de crecimiento
            parcela.actualizarEstado(Color.GOLD); // Listo para cosechar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void detenerCrecimiento() {
        enCrecimiento = false;
    }
}
