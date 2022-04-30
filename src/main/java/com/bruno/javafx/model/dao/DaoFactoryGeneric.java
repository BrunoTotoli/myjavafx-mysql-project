package com.bruno.javafx.model.dao;

import com.bruno.javafx.db.DB;

public class DaoFactoryGeneric {

    public static GenericDao createMilkDao() {
        return new MilkDao(DB.getConnection());
    }

}
