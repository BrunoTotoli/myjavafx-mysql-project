package com.bruno.javafx.model.dao;

import java.util.List;

public interface GenericDao<T> {


    void insert(T t);

    List<T> findAll();

    List<T> findSpecifiedMonth(int month);

}
