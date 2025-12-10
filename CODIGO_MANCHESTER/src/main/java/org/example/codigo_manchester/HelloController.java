package org.example.codigo_manchester;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class HelloController {

    @FXML
    private Canvas canvas;

    @FXML
    private Label statusLabel;

    private static final int BIT_WIDTH = 40;
    private static final int HIGH = 50;
    private static final int LOW = 150;

    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);

    private String[] secuencias = {"11001", "10101"}; // se pueden cambiar

    @FXML
    protected void onGenerateClick() {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

        // Productor: genera transiciones Manchester
        Thread productor = new Thread(() -> {
            try {
                for (String sec : secuencias) {
                    for (char bit : sec.toCharArray()) {
                        if (bit == '1') {
                            queue.put(LOW);
                            queue.put(HIGH);
                        } else {
                            queue.put(HIGH);
                            queue.put(LOW);
                        }
                    }
                    queue.put(-1); // fin de secuencia
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Consumidor: dibuja en Canvas
        Thread consumidor = new Thread(() -> {
            try {
                double x = 0;
                for (int i = 0; i < secuencias.length; i++) {
                    int prevY = queue.take();
                    while (true) {
                        int y = queue.take();
                        if (y == -1) break;

                        double xMid = x + BIT_WIDTH / 2.0;
                        gc.strokeLine(x, prevY, xMid, prevY);           // horizontal
                        gc.strokeLine(xMid, prevY, xMid, y);           // vertical
                        x += BIT_WIDTH / 2.0;
                        prevY = y;
                    }
                    x += BIT_WIDTH / 2.0; // separación entre secuencias
                }
                statusLabel.setText("Secuencias dibujadas correctamente!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        productor.start();
        consumidor.start();
    }
}
