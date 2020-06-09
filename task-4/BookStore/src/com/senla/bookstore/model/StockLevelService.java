package com.senla.bookstore.model;

public class StockLevelService {
    public StockLevel[] addStocklevel(StockLevel[] arrayOfStocklevels, StockLevel stockLevel){
        StockLevel[] copyOfArray = new StockLevel[arrayOfStocklevels.length + 1];
        System.arraycopy(arrayOfStocklevels, 0, copyOfArray, 0, arrayOfStocklevels.length);
        copyOfArray[copyOfArray.length - 1] = stockLevel;
        return copyOfArray;
    }
}
