package com.bruno.javafx.controllers;

import com.bruno.javafx.Main;
import com.bruno.javafx.model.services.MilkService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private MenuItem menuItemInsert;

    @FXML
    private MenuItem menuItemDelete;

    @FXML
    private MenuItem menuItemUpdate;

    @FXML
    private MenuItem menuItemShowData;


    @FXML
    private void onMenuItemInsert() {
        System.out.println("onMenuItemInsert");
    }

    @FXML
    private void onMenuItemUpdate() {
        System.out.println("onMenuItemUpdate");
    }

    @FXML
    private void onMenuItemDelete() {
        System.out.println("onMenuItemDelete");
    }

    @FXML
    private void onMenuItemShowData() {
        loadView();
    }

    private void loadView(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            VBox newVBox = loader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVBox.getChildren());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InsertView.fxml"));

            VBox newVBox = loader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVBox.getChildren());
            InsertViewController controller = loader.getController();
            controller.setService(new MilkService());
            controller.updateTableView();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}