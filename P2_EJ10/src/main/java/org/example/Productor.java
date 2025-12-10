package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Productor extends Thread {
    private final Buffer buffer;
    private final String archivo;

    public Productor(Buffer buffer, String archivo) {
        this.buffer = buffer;
        this.archivo = archivo;
    }

    @Override
    public void run() {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            int caracter;
            while ((caracter = lector.read()) != -1) { // lee carácter a carácter
                buffer.producir((char) caracter);
                Thread.sleep(50); // opcional, para simular tiempo de producción
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            buffer.finalizar();
            System.out.println("Productor finalizado.");
        }
    }
}
