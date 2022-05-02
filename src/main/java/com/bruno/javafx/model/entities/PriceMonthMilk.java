package com.bruno.javafx.model.entities;


public class PriceMonthMilk {
    private Integer year;
    private Integer month;
    private Double priceMilkInMonth;

    public PriceMonthMilk(Integer year, Integer month, Double priceMilkInMonth) {
        this.year = year;
        this.month = month;
        this.priceMilkInMonth = priceMilkInMonth;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getPriceMilkInMonth() {
        return priceMilkInMonth;
    }

    public void setPriceMilkInMonth(Double priceMilkInMonth) {
        this.priceMilkInMonth = priceMilkInMonth;
    }

    @Override
    public String toString() {
        return "PriceMonthMilk{" +
                "year='" + year + '\'' +
                ", month=" + month +
                ", priceMilkInMonth=" + priceMilkInMonth +
                '}';
    }
}
