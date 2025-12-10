package org.example;
import java.util.concurrent.BlockingQueue;

public class Consumidor extends Thread {

    private BlockingQueue<Character> cola;
    private DiscoAlberti disco;

    public Consumidor(BlockingQueue<Character> cola, DiscoAlberti disco) {
        this.cola = cola;
        this.disco = disco;
    }

    @Override
    public void run() {
        try {
            StringBuilder resultado = new StringBuilder();

            while (true) {
                char c = cola.take(); // saca de la cola

                if (c == '\0') break; // fin del mensaje

                resultado.append(disco.descifrar(c));
            }

            System.out.println("Mensaje descifrado: " + resultado);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
