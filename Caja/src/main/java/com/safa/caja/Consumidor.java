package com.safa.caja;

class Consumidor extends Thread {
    private Cajita cajita;

    public Consumidor(Cajita c) {
        this.cajita = c;
    }

    public void run() {
        while (true) {
            // Voy a la caja a ver qué hay
            String textoRecibido = cajita.cogerMensaje(); // Espero si está vacía

            // Si el papel dice "FIN", rompo el bucle y termino
            if (textoRecibido.equals("FIN")) {
                break;
            }

            System.out.println("He leído: " + textoRecibido);
        }
    }
}
