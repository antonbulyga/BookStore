package com.senla.bookstore.model.entity;

import java.util.List;

public class Stock {
   private List<StockLevel> arrayOfStockLevels;

    public Stock(List<StockLevel> arrayOfStockLevels) {
        this.arrayOfStockLevels = arrayOfStockLevels;
    }

    public List<StockLevel> getArrayOfStockLevels() {
        return arrayOfStockLevels;
    }

    public void setArrayOfStockLevels(List<StockLevel> arrayOfStockLevels) {
        this.arrayOfStockLevels = arrayOfStockLevels;
    }
}
