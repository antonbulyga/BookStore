package main.java.com.senla.model.сontrollers;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyAutoWired;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.StockLevel;
import main.java.com.senla.model.service.api.StockLevelService;

import java.util.List;

public class StockLevelController {
    private static StockLevelController instance;
    @MyAutoWired
    private StockLevelService stockLevelService;


    private StockLevelController(){

    }

    public static StockLevelController getInstance(){
        if(instance == null){
            instance = new StockLevelController();
        }
        return instance;
    }

    public void stockLevelsUpdate(Book book) {
        stockLevelService.stockLevelsUpdate(book);
    }

    public List<StockLevel> getArrayOfStockLevels(){
        List<StockLevel> stockLevels = stockLevelService.getListOfStockLevels();
        return stockLevels;
    }

    public void setListOfStockLevels(List<StockLevel> stockLevels){
        stockLevelService.setListOfStockLevels(stockLevels);
    }

    public void setArrayOfStockLevels(List<StockLevel> stockLevels){
        stockLevelService.setArrayOfStockLevels(stockLevels);
    }
}
