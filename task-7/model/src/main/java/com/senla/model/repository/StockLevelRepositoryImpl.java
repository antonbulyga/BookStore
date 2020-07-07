package main.java.com.senla.model.repository;

import main.java.com.senla.model.entity.StockLevel;

import java.util.ArrayList;
import java.util.List;

public class StockLevelRepositoryImpl {
    private static StockLevelRepositoryImpl instance;
    private List<StockLevel> listOfStockLevels = new ArrayList<>();

    private StockLevelRepositoryImpl(){

    }

    public static StockLevelRepositoryImpl getInstance(){
        if(instance == null){
            instance = new StockLevelRepositoryImpl();
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
