module org.example.p2_ej8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.p2_ej8 to javafx.fxml;
    exports org.example.p2_ej8;
}