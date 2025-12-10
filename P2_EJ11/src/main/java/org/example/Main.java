package org.example;

public class Main {
    public static void main(String[] args) {
        int numJugadores = 3;

        Arbitro arbitro = new Arbitro(numJugadores);

        Jugador jugador1 = new Jugador(1, arbitro);
        Jugador jugador2 = new Jugador(2, arbitro);
        Jugador jugador3 = new Jugador(3, arbitro);

        jugador1.start();
        jugador2.start();
        jugador3.start();

        try {
            jugador1.join();
            jugador2.join();
            jugador3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Juego finalizado.");
    }
}