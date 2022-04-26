module com.example.javafxmyproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bruno.javafx to javafx.fxml;
    opens com.bruno.javafx.controllers;
    exports com.bruno.javafx;
}