package com.safa.psp.Alberti;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Cola {

    private final BlockingQueue<Character> cola = new LinkedBlockingQueue<>();

    public void put(char c) {
        try {
            cola.put(c);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public char get() {
        try {
            return cola.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return '$'; // marca de fin
        }
    }
}
