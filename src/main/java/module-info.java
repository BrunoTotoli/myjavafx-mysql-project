module com.example.javafxmyproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bruno.javafx to javafx.fxml;
    exports com.bruno.javafx;
    exports com.bruno.javafx.controllers;
    opens com.bruno.javafx.controllers;
    opens com.bruno.javafx.model.entities;
    opens com.bruno.javafx.model.services;





}