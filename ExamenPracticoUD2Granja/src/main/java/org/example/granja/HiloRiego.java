package org.example.granja;

import javafx.application.Platform;
import javafx.scene.shape.Circle;

public class HiloRiego extends Thread {
    private ControlParcela[] parcelas;
    private Circle[] aspersores;
    private boolean pausado = false;

    public HiloRiego(ControlParcela[] parcelas, Circle[] aspersores) {
        this.parcelas = parcelas;
        this.aspersores = aspersores;
    }

    public boolean alternarPausa() {
        this.pausado = !this.pausado;
        if (!pausado) {
            // Despertamos al hilo cuando quitamos la pausa
            synchronized(this) { notifyAll(); }
        }
        return this.pausado;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 1. EL FRENO: Comprobar si el usuario pulsó pausa
                synchronized(this) {
                    while (pausado) {
                        System.out.println("Hilo Riego: En espera por pausa...");
                        wait(); // Se duerme aquí hasta el notifyAll()
                    }
                }

                // 2. LA LÓGICA: Buscar parcelas secas
                for (int i = 0; i < parcelas.length; i++) {
                    if (parcelas[i].getHumedad() < 20) {
                        final int idx = i;

                        // Activar riego
                        parcelas[idx].suministrarAgua();
                        Platform.runLater(() -> aspersores[idx].setVisible(true));

                        Thread.sleep(2000); // Tiempo regando

                        parcelas[idx].detenerRiego();
                        Platform.runLater(() -> aspersores[idx].setVisible(false));
                    }
                }
                Thread.sleep(1000); // Frecuencia de escaneo
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}