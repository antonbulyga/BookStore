package main.java.com.senla.model.сontrollers;

import main.java.com.senla.model.entity.StockLevel;
import main.java.com.senla.model.service.StockLevelService;

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
