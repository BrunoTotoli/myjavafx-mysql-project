package com.bruno.javafx.controllers;

import com.bruno.javafx.Main;
import com.bruno.javafx.model.entities.Milk;
import com.bruno.javafx.model.services.MilkService;
import com.bruno.javafx.util.Alerts;
import com.bruno.javafx.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
    private Button buttonInsert;

    @FXML
    private void onButtonInsert(ActionEvent event) {
        Stage stage = Utils.currentStage(event);
        createDialogForm("/fxml/InsertForm.fxml", stage);
        System.out.println("Insert Button");
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
        list = service.getList();

        obsList = FXCollections.observableArrayList(list);
        tableViewMilk.setItems(obsList);
    }

    private void createDialogForm(String absoluteName, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Enter Milk Data");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();

        } catch (IOException e) {
            Alerts.showAlert("IOException", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }
}
