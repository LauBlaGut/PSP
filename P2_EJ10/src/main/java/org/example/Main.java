package org.example;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        String archivo = "archivo.txt"; // tu fichero de texto

        Productor productor = new Productor(buffer, archivo);
        Consumidor consumidor1 = new Consumidor(buffer, "Consumidor 1");
        Consumidor consumidor2 = new Consumidor(buffer, "Consumidor 2");

        productor.start();
        consumidor1.start();
        consumidor2.start();

        try {
            productor.join();
            consumidor1.join();
            consumidor2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(consumidor1.getName() + " estado: " + consumidor1.getState());
        System.out.println(consumidor2.getName() + " estado: " + consumidor2.getState());
        System.out.println("Todos los hilos han finalizado.");
    }
}
