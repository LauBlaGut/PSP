package org.example;
import java.util.concurrent.BlockingQueue;

public class Productor extends Thread {

    private BlockingQueue<Character> cola;
    private String mensaje;

    public Productor(BlockingQueue<Character> cola, String mensaje) {
        this.cola = cola;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        try {
            for (char c : mensaje.toCharArray()) {
                cola.put(c); // mete letra en la cola
                Thread.sleep(10); // simula tiempo de producción
            }
            cola.put('\0'); // fin
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
