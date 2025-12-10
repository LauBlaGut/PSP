module org.example.codigo_manchester {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.codigo_manchester to javafx.fxml;
    exports org.example.codigo_manchester;
}