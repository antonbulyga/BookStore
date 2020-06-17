package com.senla.bookstore.model.сontrollers;

import com.senla.bookstore.model.StockLevel;
import com.senla.bookstore.service.StockLevelService;

import java.util.List;

public class StockLevelController {
    private static StockLevelController instance;

    private StockLevelController(){

    }

    public static StockLevelController getInstance(){
        if(instance == null){
            instance = new StockLevelController();
        }
        return instance;
    }

    public List<StockLevel> getArrayOfStockLevels(){
        List<StockLevel> stockLevels = StockLevelService.getInstance().getArrayOfStockLevels();
        return stockLevels;
    }

    public void setArrayOfStockLevels(List<StockLevel> stockLevels){
        StockLevelService.getInstance().setArrayOfStockLevels(stockLevels);
    }
}
