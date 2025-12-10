package org.example;

public class Consumidor extends Thread {
    private final Buffer buffer;

    public Consumidor(Buffer buffer, String nombre) {
        super(nombre);
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = buffer.consumir();
                if (c == 0) break; // fin de datos
                System.out.println(getName() + " consumió: " + c);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(getName() + " finalizado.");
        }
    }
}
