package com.bruno.javafx.controllers;

import com.bruno.javafx.Main;
import com.bruno.javafx.model.dao.DaoFactoryGeneric;
import com.bruno.javafx.model.dao.GenericDao;
import com.bruno.javafx.model.dao.MilkDao;
import com.bruno.javafx.model.services.MilkService;
import com.bruno.javafx.gui.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainViewController implements Initializable {

    @FXML
    private MenuItem menuItemShowData;

    @FXML
    private void onMenuItemShowData() {
        loadView("/fxml/ListView.fxml", (InsertViewController controller) -> {
            controller.setService(new MilkService());
            controller.setMilkDao(DaoFactoryGeneric.createMilkDao());
            controller.updateTableViewAll();
        });
    }



    private synchronized <T> void loadView(String path, Consumer<T> initAction) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            VBox newVBox = loader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVBox.getChildren());

            T controller = loader.getController();
            initAction.accept(controller);
        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}