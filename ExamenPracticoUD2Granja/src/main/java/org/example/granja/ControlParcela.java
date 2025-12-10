package org.example.granja;

public class ControlParcela {
    private double humedad = 50.0; // Empieza al 50%
    private boolean siendoRegada = false;

    // El hilo de cultivo llama aquí para intentar crecer
    public synchronized void intentarCrecer() throws InterruptedException {
        while (humedad <= 0) {
            System.out.println("Parcela seca: Crecimiento pausado.");
            wait(); // El hilo de cultivo se duerme hasta que llegue agua
        }
        humedad -= 10; // Consume humedad al crecer
    }

    // El hilo de riego llama aquí
    public synchronized void suministrarAgua() {
        this.humedad = 100.0;
        this.siendoRegada = true;
        notifyAll(); // Despierta al cultivo para que siga creciendo
    }

    public synchronized void detenerRiego() {
        this.siendoRegada = false;
    }

    public double getHumedad() { return humedad; }
    public boolean isSiendoRegada() { return siendoRegada; }
}