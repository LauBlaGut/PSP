public class Arbitro {

    private int numJugadores;
    private int turno;
    private int numeroAAdivinar;
    private boolean juegoAcabado;

    public Arbitro(int numJugadores) {
        this.numJugadores = numJugadores;
        this.turno = 1;
        this.numeroAAdivinar = 1 + (int)(10 * Math.random());
        this.juegoAcabado = false;

        System.out.println("El árbitro ha pensado un número entre 1 y 10.");
    }

    public synchronized int getTurno() {
        return turno;
    }

    public synchronized boolean isJuegoAcabado() {
        return juegoAcabado;
    }

    public synchronized void jugar(int idJugador, int numeroJugado) {

        if (juegoAcabado) {
            return;
        }

        System.out.println("Jugador " + idJugador + " juega el número " + numeroJugado);

        if (numeroJugado == numeroAAdivinar) {
            System.out.println("¡¡Jugador " + idJugador + " ha acertado el número " + numeroAAdivinar + "!!");
            juegoAcabado = true;
        } else {
            turno = (turno % numJugadores) + 1;
        }
    }
}
