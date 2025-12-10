package org.example.examenpracticoud2;

public class SemaforoControl {
    // Usamos un booleano simple: true = verde, false = rojo
    private boolean esVerde = false;

    public synchronized void cambiarAVerde() {
        esVerde = true;
        notifyAll();
    }

    // Método que llama el controlador para cerrar el paso
    public synchronized void cambiarARojo() {
        esVerde = false;
    }

    // Método que llaman los coches al llegar a la intersección
    public synchronized void esperarSiRojo() throws InterruptedException {
        while (!esVerde) {
            // El hilo libera el 'lock' y espera hasta que alguien llame a notifyAll()
            wait();
        }
        // Una vez que esVerde es true y nos notifican, salimos del bucle y el coche avanza.
    }
}