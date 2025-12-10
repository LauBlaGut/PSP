package com.safa.carrera;

public class Carrera {

    private int posTortuga = 1;
    private int posLiebre = 1;
    private boolean fin = false;

    private HelloController ui;

    public Carrera(HelloController ui) {
        this.ui = ui;
    }

    public synchronized void moverTortuga(int mov) {
        posTortuga += mov;
        if (posTortuga < 1) posTortuga = 1;
        if (posTortuga > 70) posTortuga = 70;
    }

    public synchronized void moverLiebre(int mov) {
        posLiebre += mov;
        if (posLiebre < 1) posLiebre = 1;
        if (posLiebre > 70) posLiebre = 70;
    }

    public synchronized boolean haTerminado() {
        return fin;
    }

    public synchronized void verificarGanador() {
        if (posTortuga >= 70 && posLiebre >= 70) {
            ui.mostrarGanador("¡Empate!");
            fin = true;
        } else if (posTortuga >= 70) {
            ui.mostrarGanador("¡Gana la TORTUGA!");
            fin = true;
        } else if (posLiebre >= 70) {
            ui.mostrarGanador("¡Gana la LIEBRE!");
            fin = true;
        }
    }

    public void iniciar() {
        Thread t1 = new Tortuga(this);
        Thread t2 = new Liebre(this);

        t1.start();
        t2.start();

        Thread refresco = new Thread(() -> {
            while (!haTerminado()) {
                ui.actualizarCasillas(posTortuga, posLiebre);
                verificarGanador();
                try { Thread.sleep(1000); } catch (Exception e) {}
            }
        });

        refresco.start();
    }
}