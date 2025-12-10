package com.safa.carrera;

public class Liebre extends Thread {

    private Carrera carrera;

    public Liebre(Carrera c) {
        this.carrera = c;
    }

    @Override
    public void run() {
        while (!carrera.haTerminado()) {

            try { Thread.sleep(1000); } catch (Exception e) {}

            int r = (int)(Math.random()*100)+1;
            int mov;

            if (r <= 20) mov = 0;
            else if (r <= 40) mov = 9;
            else if (r <= 50) mov = -12;
            else if (r <= 80) mov = 1;
            else mov = -2;

            carrera.moverLiebre(mov);
        }
    }
}