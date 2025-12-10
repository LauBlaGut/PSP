package org.example.examenpracticoud2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class HelloController {
    @FXML private Label welcomeText;
    @FXML private Pane panelCarretera;

    // Nuestro objeto compartido para sincronizar hilos
    private SemaforoControl semaforo = new SemaforoControl();
    private Circle circuloLuz;

    @FXML
    public void initialize() {
        // Dibujamos el semáforo visual al arrancar
        circuloLuz = new Circle(150, 50, 15, Color.RED);
        panelCarretera.getChildren().add(circuloLuz);

        // Hilo que cambia el semáforo solo (Automático)
        Thread hiloSemaforo = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(3000); // 3 seg Rojo
                    semaforo.cambiarAVerde();
                    circuloLuz.setFill(Color.GREEN);

                    Thread.sleep(3000); // 3 seg Verde
                    semaforo.cambiarARojo();
                    circuloLuz.setFill(Color.RED);
                }
            } catch (InterruptedException e) { e.printStackTrace(); }
        });
        hiloSemaforo.setDaemon(true); // Para que se cierre al cerrar la app
        hiloSemaforo.start();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("¡Coche en marcha!");

        // Creamos un rectángulo (el coche)
        Rectangle coche = new Rectangle(10, 100, 30, 20);
        coche.setFill(Color.CYAN);
        panelCarretera.getChildren().add(coche);

        // Lanzamos un nuevo hilo por cada coche
        Thread hiloCoche = new Thread(new HiloVehiculo(coche, semaforo));
        hiloCoche.start();
    }
}