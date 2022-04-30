package com.bruno.javafx.model.dao;

import com.bruno.javafx.db.DBException;
import com.bruno.javafx.model.entities.Milk;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MilkDao implements GenericDao<Milk> {

    private Connection connection;

    public MilkDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Milk milk) {
        try {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement("insert into milk(date_milk,quantity) values (?,?)");
                statement.setDate(1, new Date(milk.getDate().getTime()));
                statement.setDouble(2, milk.getQuantity());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {

        }
    }

    @Override
    public List<Milk> findAll() {
        List<Milk> milkList = new ArrayList<>();
        try {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement("select * from milk");
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Milk milk = new Milk(resultSet.getDate("date_milk"), resultSet.getDouble("quantity"));
                    milkList.add(milk);
                }
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {

        }
        return milkList;
    }
    @Override
    public List<Milk> findSpecifiedMonth(int month) {
        List<Milk> milkList = new ArrayList<>();
        try {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement("select id,date_milk,quantity from milk where MONTH(date_milk)= ?");
                statement.setInt(1, month);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Milk milk = new Milk(resultSet.getDate("date_milk"), resultSet.getDouble("quantity"));
                    milkList.add(milk);
                }
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {

        }
        return milkList;
    }
}
