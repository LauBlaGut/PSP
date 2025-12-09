public class Main {
    public static void main(String[] args) {

        int numJugadores = 3; // Cambia el número si quieres más jugadores

        Arbitro arbitro = new Arbitro(numJugadores);

        Jugador[] jugadores = new Jugador[numJugadores];

        for (int i = 0; i < numJugadores; i++) {
            jugadores[i] = new Jugador(i + 1, arbitro);
            jugadores[i].start();
        }

        for (int i = 0; i < numJugadores; i++) {
            try {
                jugadores[i].join();
            } catch (InterruptedException e) {}
        }

        System.out.println("Partida finalizada.");
    }
}