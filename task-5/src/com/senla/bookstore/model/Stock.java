package com.senla.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Stock {
   private List<StockLevel> arrayOfStockLevels = new ArrayList<>();

    public List<StockLevel> getArrayOfStockLevels() {
        return arrayOfStockLevels;
    }

    public void setArrayOfStockLevels(List<StockLevel> arrayOfStockLevels) {
        this.arrayOfStockLevels = arrayOfStockLevels;
    }
}
