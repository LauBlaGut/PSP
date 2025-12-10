package org.example.granja;

import javafx.event.ActionEvent; // 1. CAMBIO: Importación correcta de JavaFX
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class HelloController {
    @FXML private GridPane gridParcelas;
    @FXML private Label statusLabel; // 2. AÑADIDO: Referencia al Label del FXML

    private ControlParcela[] controles = new ControlParcela[4];
    private Circle[] aspersores = new Circle[4];

    // 3. AÑADIDO: Creamos el hilo de riego como variable para poder pausarlo
    private HiloRiego hiloRiego;

    @FXML
    public void initialize() {
        for (int i = 0; i < 4; i++) {
            controles[i] = new ControlParcela();

            Rectangle rect = new Rectangle(80, 80, Color.LIGHTGRAY);
            final int index = i;
            rect.setOnMouseClicked(e -> plantar(index, rect));

            aspersores[i] = new Circle(10, Color.DEEPSKYBLUE);
            aspersores[i].setVisible(false);

            gridParcelas.add(rect, i % 2, i / 2);
            gridParcelas.add(aspersores[i], i % 2, i / 2);
        }

        // Iniciamos el hilo de riego guardando la referencia
        hiloRiego = new HiloRiego(controles, aspersores);
        hiloRiego.start();
    }

    private void plantar(int index, Rectangle rect) {
        if (rect.getFill() == Color.LIGHTGRAY) {
            new HiloCultivo(rect, controles[index]).start();
        }
    }

    @FXML
    private void onPausaRiegoClick(ActionEvent event) {
        // 4. LÓGICA DE PAUSA: Llamamos al método togglePausa del hilo o monitor
        // Asumimos que hiloRiego tiene acceso al flag de pausa del monitor global
        boolean pausado = hiloRiego.alternarPausa();

        if (pausado) {
            statusLabel.setText("Estado: Riego PAUSADO");
            statusLabel.setTextFill(Color.RED);
        } else {
            statusLabel.setText("Estado: Riego ACTIVO");
            statusLabel.setTextFill(Color.GREEN);
        }
    }

    @FXML
    private void onReiniciarClick(ActionEvent event) {
        // 1. Limpiar visualmente la granja
        gridParcelas.getChildren().clear();

        // 2. Resetear los monitores de control
        for (int i = 0; i < controles.length; i++) {
            controles[i] = new ControlParcela();
        }

        // 3. Volver a generar las parcelas vacías
        initialize(); // Llamamos a initialize para reconstruir la cuadrícula

        // 4. Actualizar el estado
        statusLabel.setText("Estado: Simulación Reiniciada");
        statusLabel.setTextFill(Color.BLUE);
    }
}