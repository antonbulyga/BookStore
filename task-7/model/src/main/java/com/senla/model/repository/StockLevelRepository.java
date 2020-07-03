package main.java.com.senla.model.repository;

import main.java.com.senla.model.entity.StockLevel;

import java.util.ArrayList;
import java.util.List;

public class StockLevelRepository {
    private static StockLevelRepository instance;
    private List<StockLevel> listOfStockLevels = new ArrayList<>();

    private StockLevelRepository(){

    }

    public static StockLevelRepository getInstance(){
        if(instance == null){
            instance = new StockLevelRepository();
        }
        return instance;
    }

    public List<StockLevel> getListOfStockLevels() {
        return listOfStockLevels;
    }

    public void setListOfStockLevels(List<StockLevel> listOfStockLevels) {
        this.listOfStockLevels = listOfStockLevels;
    }
}
