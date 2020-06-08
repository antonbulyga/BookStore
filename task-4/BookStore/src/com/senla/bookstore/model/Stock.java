package com.senla.bookstore.model;

public class Stock {
   private StockLevel[] arrayOfStockLevels;

    public StockLevel[] getArrayOfStockLevels() {
        return arrayOfStockLevels;
    }

    public void setArrayOfStockLevels(StockLevel[] arrayOfStockLevels) {
        this.arrayOfStockLevels = arrayOfStockLevels;
    }
}
