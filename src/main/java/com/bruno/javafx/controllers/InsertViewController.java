package com.bruno.javafx.controllers;

import com.bruno.javafx.Main;
import com.bruno.javafx.model.entities.Milk;
import com.bruno.javafx.model.services.MilkService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class InsertViewController implements Initializable {

    ObservableList<Milk> obsList;
    private MilkService service;

    public void setService(MilkService service) {
        this.service = service;
    }

    @FXML
    private TableView<Milk> tableViewMilk;

    @FXML
    private TableColumn<Milk, Date> tableColumnDate;

    @FXML
    private TableColumn<Milk, Double> tableColumnQuantity;

    private void initializeNodes() {
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewMilk.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service was null");
        }
        List<Milk> list = null;
        try {
            list = service.findAll();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        obsList = FXCollections.observableArrayList(list);
        tableViewMilk.setItems(obsList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }
}
