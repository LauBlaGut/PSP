module org.example.granja {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.granja to javafx.fxml;
    exports org.example.granja;
}