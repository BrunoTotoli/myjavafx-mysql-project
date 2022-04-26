package com.bruno.javafx.model.services;

import com.bruno.javafx.model.entities.Milk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MilkService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public List<Milk> findAll() throws ParseException {
        return List.of(new Milk(sdf.parse("25/04/2021"),120D),new Milk(sdf.parse("24/04/2021"),140D));
    }



}
