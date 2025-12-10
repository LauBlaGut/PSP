module org.example.examenpracticoud2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.examenpracticoud2 to javafx.fxml;
    exports org.example.examenpracticoud2;
}