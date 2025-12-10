package com.safa.carrera;

public class Tortuga extends Thread {

    private Carrera carrera;

    public Tortuga(Carrera c) {
        this.carrera = c;
    }

    @Override
    public void run() {
        while (!carrera.haTerminado()) {

            try { Thread.sleep(1000); } catch (Exception e) {}

            int r = (int)(Math.random()*100)+1;
            int mov;

            if (r <= 50) mov = 3;
            else if (r <= 70) mov = -6;
            else mov = 1;

            carrera.moverTortuga(mov);
        }
    }
}