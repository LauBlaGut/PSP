package org.example.p2_ej8;

public class MiHilo extends Thread {

    private boolean activo = true;
    private boolean suspendido = false;
    private int contador = 0;
    private int tiempo;

    public MiHilo(String nombre, int tiempo) {
        super(nombre);
        this.tiempo = tiempo;
    }

    @Override
    public void run() {
        while (activo) {
            synchronized (this) {
                while (suspendido) {
                    try {
                        wait(); // Se bloquea hasta que alguien llame a notify()
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }

            // Hacer el trabajo del hilo
            contador++;
            System.out.println(getName() + " contador = " + contador);

            try {
                Thread.sleep(tiempo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println(getName() + " finaliza con contador = " + contador);
    }

    // Suspender hilo
    public synchronized void suspenderHilo() {
        suspendido = true;
    }

    // Reanudar hilo
    public synchronized void reanudarHilo() {
        suspendido = false;
        notify();
    }

    // Finalizar hilo
    public void finalizar() {
        activo = false;
        reanudarHilo(); // Por si está suspendido
    }

    // Saber si está suspendido
    public boolean isSuspendido() {
        return suspendido;
    }

    // Obtener contador
    public int getContador() {
        return contador;
    }
}
