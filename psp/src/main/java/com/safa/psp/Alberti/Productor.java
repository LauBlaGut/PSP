package com.safa.psp.Alberti;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Productor extends Thread {

    private final Cola cola;

    public Productor(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            InputStream is = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("com/safa/psp/datos/mensaje.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int c;
            while ((c = br.read()) != -1) {
                char ch = (char) c;

                if (ch == ' ' || ch == '\n' || ch == '\r') continue;

                cola.put(ch);
            }

            cola.put('$');

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
