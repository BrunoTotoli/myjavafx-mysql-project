package com.bruno.javafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}