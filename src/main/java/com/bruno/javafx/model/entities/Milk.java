package com.bruno.javafx.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Milk {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Date date;
    private Double quantity;

    public Milk(Date date, Double quantity) {
        this.date = date;
        this.quantity = quantity;
    }

    public Milk() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milk milk = (Milk) o;
        return Objects.equals(date, milk.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return "Milk{" +
                "date=" + sdf.format(date) +
                ", quantity=" + quantity +
                '}';
    }
}
