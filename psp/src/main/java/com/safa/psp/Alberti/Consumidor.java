package com.safa.psp.Alberti;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class Consumidor extends Thread {

    private final Cola cola;
    private final TextArea area;
    private final Alberti alberti = new Alberti();
    private final Nomenclator nom = new Nomenclator();

    private final StringBuilder bufferNumero = new StringBuilder();
    private final StringBuilder mensajeFinal = new StringBuilder();

    public Consumidor(Cola cola, TextArea area) {
        this.cola = cola;
        this.area = area;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = cola.get();

                if (c == '$') break;

                if (Character.isDigit(c)) {
                    bufferNumero.append(c);

                    if (bufferNumero.length() == 3) {
                        String palabra = nom.traducir(bufferNumero.toString());
                        mensajeFinal.append(palabra);
                        bufferNumero.setLength(0);

                        final String linea = "NUM " + bufferNumero + " → " + palabra;
                        Platform.runLater(() -> area.appendText(linea + "\n"));
                    }

                    continue;
                }

                char claro = alberti.descifrar(c);
                mensajeFinal.append(claro);

                final String linea = c + " → " + claro;
                Platform.runLater(() -> area.appendText(linea + "\n"));
            }

            Platform.runLater(() -> {
                area.appendText("\n--- DESCIFRADO COMPLETO ---\n");
                area.appendText(mensajeFinal.toString());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
