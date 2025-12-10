package com.safa.granja;

public class SistemaRiego implements Runnable {
    private boolean activo = true;

    @Override
    public void run() {
        while (activo) {
            try {
                // Lógica para activar/desactivar aspersores
                Thread.sleep(2000); // Simular intervalo de riego
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void detener() {
        activo = false;
    }
}
