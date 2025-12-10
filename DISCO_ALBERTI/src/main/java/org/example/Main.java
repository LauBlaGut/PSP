package org.example;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {

        BlockingQueue<Character> cola = new ArrayBlockingQueue<>(10);
        DiscoAlberti disco = new DiscoAlberti();

        String mensajeCifrado = "baa&hpmiyvsvoiylrlxckngkl";

        Productor productor = new Productor(cola, mensajeCifrado);
        Consumidor consumidor = new Consumidor(cola, disco);

        productor.start();
        consumidor.start();
    }
}
