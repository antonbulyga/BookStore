package main.java.com.senla.model.сontrollers;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.StockLevel;
import main.java.com.senla.model.service.StockLevelServiceImpl;

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

    public void stockLevelsUpdate(Book book) {
        StockLevelServiceImpl.getInstance().stockLevelsUpdate(book);
    }

    public List<StockLevel> getArrayOfStockLevels(){
        List<StockLevel> stockLevels = StockLevelServiceImpl.getInstance().getListOfStockLevels();
        return stockLevels;
    }

    public void setListOfStockLevels(List<StockLevel> stockLevels){
        StockLevelServiceImpl.getInstance().setListOfStockLevels(stockLevels);
    }

    public void setArrayOfStockLevels(List<StockLevel> stockLevels){
        StockLevelServiceImpl.getInstance().setArrayOfStockLevels(stockLevels);
    }
}
