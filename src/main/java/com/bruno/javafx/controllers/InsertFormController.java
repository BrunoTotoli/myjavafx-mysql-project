package com.bruno.javafx.controllers;

import com.bruno.javafx.model.entities.Milk;
import com.bruno.javafx.model.services.MilkService;
import com.bruno.javafx.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.ResourceBundle;


public class InsertFormController implements Initializable {

    @FXML
    private DatePicker datePickerDate;

    @FXML
    private TextField textFieldQuantity;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    @FXML
    private void onButtonSave() {
            Milk milk = new Milk(Date.from(datePickerDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Double.parseDouble(textFieldQuantity.getText()));
            MilkService milkService = new MilkService();
            milkService.addList(milk);
            System.out.println(milk);
    }

    @FXML
    private void onButtonCancel() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        Constraints.setTextFieldDouble(textFieldQuantity);
    }


}
