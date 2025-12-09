package com.safa.hilosbotones;

import javafx.application.Platform;
import javafx.scene.control.TextField;

public class HiloContador extends Thread {

    private int contador = 0;
    private int tiempoDormir;
    private boolean suspendido = false;

    private final Object monitor = new Object();
    private TextField campoTexto;

    public HiloContador(String nombre, int tiempoDormir, TextField campoTexto) {
        super(nombre);
        this.tiempoDormir = tiempoDormir;
        this.campoTexto = campoTexto;
    }

    public void suspenderHilo() {
        suspendido = true;
    }

    public void reanudarHilo() {
        synchronized (monitor) {
            suspendido = false;
            monitor.notify(); // despierta el hilo
        }
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {

                synchronized (monitor) {
                    while (suspendido) {
                        monitor.wait(); // suspende el hilo
                    }
                }

                contador++;

                Platform.runLater(() ->
                        campoTexto.setText(String.valueOf(contador))
                );

                Thread.sleep(tiempoDormir);
            }
        } catch (InterruptedException ignored) {}
    }

    public int getContador() {
        return contador;
    }
}