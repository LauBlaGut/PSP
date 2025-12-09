package com.safa.caja;

class Cajita {
    private String mensaje;
    private boolean estaLlena = false; // ¿Hay papel dentro?

    // MÉTODOS:

    // 1. Para que TÚ pongas el mensaje
    public synchronized void ponerMensaje(String texto) {
        // Si ya hay un papel, TÚ te duermes
        while (estaLlena == true) {
            try { wait(); } catch (Exception e) {}
        }

        // Si está vacía, pones el papel
        this.mensaje = texto;
        estaLlena = true; // Ahora está llena
        notifyAll(); // ME GRITAS: "¡Ya hay papel!"
    }

    // 2. Para que YO recoja el mensaje
    public synchronized String cogerMensaje() {
        // Si no hay papel, YO me duermo
        while (estaLlena == false) {
            try { wait(); } catch (Exception e) {}
        }

        // Si hay papel, lo cojo
        estaLlena = false; // Ahora está vacía
        notifyAll(); // TE GRITO: "¡Ya lo cogí, pon otro!"
        return this.mensaje;
    }
}
