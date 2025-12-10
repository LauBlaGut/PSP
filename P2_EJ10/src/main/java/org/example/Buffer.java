package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private final Queue<Character> cola = new LinkedList<>();
    private boolean fin = false;

    public synchronized void producir(char c) {
        cola.add(c);
        notifyAll(); // despierta a los consumidores
    }

    public synchronized char consumir() throws InterruptedException {
        while (cola.isEmpty() && !fin) {
            wait(); // espera hasta que haya algo
        }
        if (cola.isEmpty() && fin == true) {
            return 0; // señal de fin
        }
        return cola.poll();
    }

    public synchronized void finalizar() {
        fin = true;
        notifyAll(); // despierta a los consumidores que están esperando
    }
}
