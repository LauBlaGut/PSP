package org.example;

public class Jugador extends Thread {

    private final int idJugador;
    private final Arbitro arbitro;

    public Jugador(int idJugador, Arbitro arbitro) {
        this.idJugador = idJugador;
        this.arbitro = arbitro;
    }

    @Override
    public void run() {
        while (!arbitro.isJuegoTerminado()) {
            if (arbitro.getTurno() == idJugador) {
                int jugada = 1 + (int) (10 * Math.random());
                arbitro.jugar(idJugador, jugada);
            }
            try {
                Thread.sleep(100); // evita busy waiting
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
