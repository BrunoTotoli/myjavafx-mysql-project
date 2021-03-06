package com.bruno.javafx.model.services;

import com.bruno.javafx.model.entities.Milk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MilkService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    static List<Milk> list = new ArrayList<>();


    public void addList(Milk milk) {
        list.add(milk);
    }

    public List<Milk> getList() {
        return list;
    }
}
