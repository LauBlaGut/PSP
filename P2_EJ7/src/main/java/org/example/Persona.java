package org.example;

public class Persona extends Thread {

    private final Cuenta cuenta;

    public Persona(String nombre, Cuenta cuenta) {
        super(nombre); // Nombre del hilo
        this.cuenta = cuenta;
    }

    private int numeroRandom() {
        return (int) (Math.random() * 500 + 1);
    }

    @Override
    public void run() {
        try {
            cuenta.depositar(numeroRandom());
            Thread.sleep(100);

            cuenta.depositar(numeroRandom());
            Thread.sleep(100);

            cuenta.retirar(numeroRandom());
            Thread.sleep(100);

            cuenta.retirar(numeroRandom());
            Thread.sleep(100);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
