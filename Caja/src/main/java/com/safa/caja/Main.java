package com.safa.caja;

import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1. Truco: Creo el fichero yo mismo para que no te de error
        FileWriter fw = new FileWriter("texto.txt");
        fw.write("Hola mundo\n");
        fw.write("Esto es una prueba\n");
        fw.write("Java es divertido\n");
        fw.close();

        // 2. Creamos la CAJITA (solo hay una)
        Cajita cajitaCompartida = new Cajita();

        // 3. Te creo a TI (Productor) y a MÍ (Consumidor)
        // Y a los dos nos dan la MISMA cajita
        Productor tu = new Productor(cajitaCompartida);
        Consumidor yo = new Consumidor(cajitaCompartida);

        // 4. ¡A jugar!
        tu.start();
        yo.start();
    }
}
