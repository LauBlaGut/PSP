package com.safa.caja;

import java.io.*;

class Productor extends Thread {
    private Cajita cajita;

    public Productor(Cajita c) {
        this.cajita = c;
    }

    public void run() {
        // Imaginamos que leemos un fichero real llamado "texto.txt"
        try {
            BufferedReader br = new BufferedReader(new FileReader("texto.txt"));
            String linea;

            // Leemos línea a línea hasta que no quede nada
            while ((linea = br.readLine()) != null) {
                cajita.ponerMensaje(linea); // PONES EL PAPEL (y esperas si está lleno)
            }

            // Cuando se acaba el fichero, mandamos el mensaje de cierre
            cajita.ponerMensaje("FIN");
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
