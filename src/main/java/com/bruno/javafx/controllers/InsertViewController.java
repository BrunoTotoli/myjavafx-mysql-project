package com.bruno.javafx.controllers;

import com.bruno.javafx.Main;
import com.bruno.javafx.gui.listeners.DataChangeListener;
import com.bruno.javafx.model.dao.DaoFactoryGeneric;
import com.bruno.javafx.model.dao.GenericDao;
import com.bruno.javafx.model.entities.Milk;
import com.bruno.javafx.model.entities.Months;
import com.bruno.javafx.model.services.MilkService;
import com.bruno.javafx.gui.util.Alerts;
import com.bruno.javafx.gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class InsertViewController implements Initializable, DataChangeListener {

    ObservableList<Milk> obsList;
    ObservableList<Months> obsMonthList;
    List<Months> listMonths = Arrays.asList(Months.values());
    private MilkService service;
    private GenericDao milkDao = DaoFactoryGeneric.createMilkDao();

    public void setService(MilkService service) {
        this.service = service;
    }

    @FXML
    private Button buttonInsert;

    @FXML
    private Button buttonMedia;

    @FXML
    private ComboBox<Months> comboBoxMonths;


    public void loadAssociatedObjects() {
        obsMonthList = FXCollections.observableList(listMonths);;
        comboBoxMonths.setItems(obsMonthList);
    }


    private void initializeComboBoxDepartment() {
        Callback<ListView<Months>, ListCell<Months>> factory = lv -> new ListCell<Months>() {
            @Override
            protected void updateItem(Months item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.name());
            }
        };
        comboBoxMonths.setCellFactory(factory);
        comboBoxMonths.setButtonCell(factory.call(null));
    }


    @FXML
    private void onButtonInsert(ActionEvent event) {
        Stage stage = Utils.currentStage(event);
        createDialogForm("/fxml/InsertForm.fxml", stage);
        System.out.println("Insert Button");
    }

    @FXML
    private void onButtonMedia() {

    }

    @FXML
    private TableView<Milk> tableViewMilk;

    @FXML
    private TableColumn<Milk, Date> tableColumnDate;

    @FXML
    private TableColumn<Milk, Double> tableColumnQuantity;

    private void initializeNodes() {
        loadAssociatedObjects();
        initializeComboBoxDepartment();
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewMilk.prefHeightProperty().bind(stage.heightProperty());
        Utils.formatTableColumnDate(tableColumnDate, "dd/MM/yyyy");

    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service was null");
        }
        List<Milk> list = null;
        list = milkDao.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewMilk.setItems(obsList);
    }

    private void createDialogForm(String absoluteName, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            InsertFormController controller = loader.getController();
            controller.setService(new MilkService());
            controller.subscribeDataChangeListener(this);


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

    @Override
    public void onDataChanged() {
        updateTableView();
    }


}
