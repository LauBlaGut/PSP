public class Jugador extends Thread {

    private int idJugador;
    private Arbitro arbitro;

    public Jugador(int idJugador, Arbitro arbitro) {
        this.idJugador = idJugador;
        this.arbitro = arbitro;
    }

    @Override
    public void run() {
        while (!arbitro.isJuegoAcabado()) {

            if (arbitro.getTurno() == idJugador) {

                int numero = 1 + (int)(10 * Math.random());
                arbitro.jugar(idJugador, numero);

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {}
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }
        }

        System.out.println("Jugador " + idJugador + " termina.");
    }
}