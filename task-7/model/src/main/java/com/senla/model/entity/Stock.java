package main.java.com.senla.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stock implements Serializable {
   private List<StockLevel> arrayOfStockLevels = new ArrayList<>();

    public List<StockLevel> getArrayOfStockLevels() {
        return arrayOfStockLevels;
    }

    public void setArrayOfStockLevels(List<StockLevel> arrayOfStockLevels) {
        this.arrayOfStockLevels = arrayOfStockLevels;
    }
}
