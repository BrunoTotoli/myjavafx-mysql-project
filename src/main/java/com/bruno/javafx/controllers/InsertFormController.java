package com.bruno.javafx.controllers;

import com.bruno.javafx.gui.listeners.DataChangeListener;
import com.bruno.javafx.model.dao.DaoFactoryGeneric;
import com.bruno.javafx.model.dao.GenericDao;
import com.bruno.javafx.model.dao.MilkDao;
import com.bruno.javafx.model.entities.Milk;
import com.bruno.javafx.model.services.MilkService;
import com.bruno.javafx.gui.util.Constraints;
import com.bruno.javafx.gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class InsertFormController implements Initializable {

    private MilkService service;
    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
    private GenericDao milkDao = DaoFactoryGeneric.createMilkDao();


    @FXML
    private DatePicker datePickerDate;

    @FXML
    private TextField textFieldQuantity;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;


    @FXML
    private void onButtonSave(ActionEvent event) {
        if (datePickerDate != null) {
            Milk milk = new Milk(Date.from(datePickerDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Double.parseDouble(textFieldQuantity.getText()));
            milkDao.insert(milk);
            notifyDataChangeListeners();
            Utils.currentStage(event).close();
        }
    }

    @FXML
    private void onButtonCancel(ActionEvent actionEvent) {
        Utils.currentStage(actionEvent).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        Constraints.setTextFieldDouble(textFieldQuantity);
        Utils.formatDatePicker(datePickerDate, "dd/MM/yyyy");
    }


    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChanged();
        }
    }

    public void setService(MilkService service) {
        this.service = service;
    }
}
