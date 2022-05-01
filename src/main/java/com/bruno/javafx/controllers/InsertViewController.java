package com.bruno.javafx.controllers;

import com.bruno.javafx.Main;
import com.bruno.javafx.gui.listeners.DataChangeListener;
import com.bruno.javafx.model.dao.GenericDao;
import com.bruno.javafx.model.entities.Milk;
import com.bruno.javafx.model.enums.Months;
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
import java.util.stream.Collectors;

public class InsertViewController implements Initializable, DataChangeListener {

    private ObservableList<Milk> obsList;
    private ObservableList<Months> obsMonthList;
    private List<Months> listMonths = Arrays.asList(Months.values());
    private MilkService service;
    private GenericDao milkDao;


    @FXML
    private Button buttonInsert;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonMedia;

    @FXML
    private ComboBox<Months> comboBoxMonths;

    @FXML
    private TableView<Milk> tableViewMilk;

    @FXML
    private TableColumn<Milk, Date> tableColumnDate;

    @FXML
    private TableColumn<Milk, Double> tableColumnQuantity;

    @FXML
    private void onButtonInsert(ActionEvent event) {
        Stage stage = Utils.currentStage(event);
        createDialogForm("/fxml/InsertForm.fxml", stage);
    }

    @FXML
    private void onButtonUpdate() {
        updateTableViewMonth();
    }

    @FXML
    private void onButtonMedia() {
        List<Milk> milkMonth = obsList;
        double sum = milkMonth.stream().collect(Collectors.summingDouble(Milk::getQuantity));
        Alerts.showAlert("Media: ", null, "TotalPart:" + sum + "\nOurPart: " + String.format("%.2f", sum / 4) + "\nMilkPrice in Month: " + String.format("%.2f", 2.49) + "\nValue: " + String.format("%.2f", ((sum / 4) * 2.49)), Alert.AlertType.INFORMATION);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        loadAssociatedObjects();
        initializeComboBoxDepartment();
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewMilk.prefHeightProperty().bind(stage.heightProperty());
        Utils.formatTableColumnDate(tableColumnDate, "dd/MM/yyyy");

    }

    public void updateTableViewAll() {
        if (service == null) {
            throw new IllegalStateException("Service was null");
        }
        if (milkDao == null) {
            throw new IllegalStateException("Dao was null");
        }
        List<Milk> list = null;
        list = milkDao.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewMilk.setItems(obsList);
    }

    public void loadAssociatedObjects() {
        obsMonthList = FXCollections.observableList(listMonths);
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


    private void updateTableViewMonth() {
        boolean isRunning = true;
        Months month = null;
        if (milkDao == null) {
            throw new IllegalStateException("Dao was null");
        }
        while (isRunning) {
            for (int i = 0; i < listMonths.size(); i++) {
                if (comboBoxMonths.getValue() == listMonths.get(0)) {
                    updateTableViewAll();
                }
                if (comboBoxMonths.getValue() == listMonths.get(i)) {
                    month = listMonths.get(i);
                    if (service == null) {
                        throw new IllegalStateException("Service was null");
                    }
                    List<Milk> list = null;
                    list = milkDao.findSpecifiedMonth(month.getType());
                    obsList = FXCollections.observableArrayList(list);
                    tableViewMilk.setItems(obsList);
                    isRunning = false;
                }
            }
        }
    }

    public void setMilkDao(GenericDao milkDao) {
        this.milkDao = milkDao;
    }

    public void setService(MilkService service) {
        this.service = service;
    }

    @Override
    public void onDataChanged() {
        updateTableViewAll();
        updateTableViewMonth();
    }
}
