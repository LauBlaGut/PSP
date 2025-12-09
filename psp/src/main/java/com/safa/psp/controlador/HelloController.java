package com.safa.psp.controlador;

import com.safa.psp.Alberti.Cola;
import com.safa.psp.Alberti.Consumidor;
import com.safa.psp.Alberti.Productor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class HelloController {

    @FXML private Button btnIniciar;
    @FXML private TextArea areaTexto;

    @FXML
    private void initialize() {
        areaTexto.setEditable(false);
    }

    @FXML
    protected void onIniciar() {
        areaTexto.clear();

        Cola cola = new Cola();
        Productor productor = new Productor(cola);
        Consumidor consumidor = new Consumidor(cola, areaTexto);

        productor.start();
        consumidor.start();
    }
}
