package com.bruno.javafx.model.enums;

public enum Months {
    TODOS(0), JANEIRO(1), FEVEIRO(2), MARCO(3), ABRIL(4), MAIO(5), JUNHO(6), JULHO(7), AGOSTO(8), SETEMBRO(9), OUTUBRO(10), NOVEMBRO(11), DEZEMBRO(12);


    private int type;

    Months(int i) {
        this.type = i;
    }

    public int getType() {
        return type;
    }
}
