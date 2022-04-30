package com.bruno.javafx;

import com.bruno.javafx.db.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Scene mainScene;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            ScrollPane scrollPane = fxmlLoader.load();
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            mainScene = new Scene(scrollPane);
            stage.setTitle("Milk");
            stage.setScene(mainScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Scene getMainScene() {
        return mainScene;
    }


    public static void main(String[] args) {
        launch();
    }
}