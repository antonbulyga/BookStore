package com.senla.bookstore.model;

public class StockLevel {
    int id;
    int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public StockLevel(int id, int count) {
        this.id = id;
        this.count = count;
    }
}
