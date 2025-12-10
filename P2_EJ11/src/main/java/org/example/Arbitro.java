package org.example;

public class Arbitro {

    private final int numJugadores;
    private int turno;       // número de jugador cuyo turno es
    private final int numeroAdivinar;
    private boolean juegoTerminado;

    public Arbitro(int numJugadores) {
        this.numJugadores = numJugadores;
        this.turno = 1;  // empieza el jugador 1
        this.numeroAdivinar = 1 + (int) (10 * Math.random()); // 1-10
        this.juegoTerminado = false;

        System.out.println("Número a adivinar: " + numeroAdivinar); // para debug
    }

    public synchronized int getTurno() {
        return turno;
    }

    public synchronized boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public synchronized void jugar(int idJugador, int jugada) {
        // Solo juega si es su turno y el juego no ha terminado
        if (juegoTerminado) return;
        if (idJugador != turno) return;

        System.out.println("Jugador " + idJugador + " jugó: " + jugada);

        if (jugada == numeroAdivinar) {
            System.out.println("Jugador " + idJugador + " ha acertado! ¡Juego terminado!");
            juegoTerminado = true;
        } else {
            // Avanza al siguiente turno
            turno++;
            if (turno > numJugadores) turno = 1;
            System.out.println("Siguiente turno: jugador " + turno);
        }
    }
}
