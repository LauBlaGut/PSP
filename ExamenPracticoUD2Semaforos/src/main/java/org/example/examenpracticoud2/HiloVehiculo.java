package org.example.examenpracticoud2;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;

public class HiloVehiculo extends Thread {
    private Rectangle representacion;
    private SemaforoControl semaforo;
    private double posicionX;
    private final double STOP_LINE = 120.0;

    public HiloVehiculo(Rectangle rect, SemaforoControl sem) {
        this.representacion = rect;
        this.semaforo = sem;
        this.posicionX = 0;
    }

    @Override
    public void run() {
        try {
            // El coche se mueve hasta el final del Pane (ej. 400 píxeles)
            while (posicionX < 400) {

                // --- LÓGICA DE SINCRONIZACIÓN ---
                // Si el coche entra en la zona del semáforo:
                if (posicionX >= STOP_LINE - 5 && posicionX <= STOP_LINE) {
                    System.out.println("Vehículo " + this.getName() + " esperando al semáforo...");

                    // Llamamos al método sincronizado.
                    // Si está en rojo, aquí el hilo se quedará dormido (congelado).
                    semaforo.esperarSiRojo();

                    System.out.println("Vehículo " + this.getName() + " reanuda la marcha.");
                }

                // --- MOVIMIENTO ---
                posicionX += 5; // El coche avanza 5 píxeles

                // --- ACTUALIZACIÓN GRÁFICA ---
                // No podemos tocar 'representacion' directamente fuera del hilo de UI
                Platform.runLater(() -> representacion.setTranslateX(posicionX));

                // Controlamos la velocidad de la animación (más alto = más lento)
                Thread.sleep(50);
            }

            // Cuando sale de la pantalla, podemos eliminarlo del panel si queremos
            Platform.runLater(() -> representacion.setVisible(false));

        } catch (InterruptedException e) {
            System.err.println("El vehículo fue interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}