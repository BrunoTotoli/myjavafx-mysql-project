package com.bruno.javafx.model.dao;

import com.bruno.javafx.db.DB;
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
        PreparedStatement statement = null;
        try {
            if (connection != null) {
                statement = connection.prepareStatement("insert into milk(date_milk,quantity) values (?,?)");
                statement.setDate(1, new Date(milk.getDate().getTime()));
                statement.setDouble(2, milk.getQuantity());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public List<Milk> findAll() {
        List<Milk> milkList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            if (connection != null) {
                statement = connection.prepareStatement("select * from milk");
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Milk milk = new Milk(resultSet.getDate("date_milk"), resultSet.getDouble("quantity"));
                    milkList.add(milk);
                }
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
        return milkList;
    }

    @Override
    public List<Milk> findSpecifiedMonth(int month) {
        List<Milk> milkList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            if (connection != null) {
                statement = connection.prepareStatement("select id,date_milk,quantity from milk where MONTH(date_milk)= ?");
                statement.setInt(1, month);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Milk milk = new Milk(resultSet.getDate("date_milk"), resultSet.getDouble("quantity"));
                    milkList.add(milk);
                }
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
        return milkList;
    }
}
