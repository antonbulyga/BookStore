package main.java.com.senla.model.repository;

import main.java.com.senla.model.entity.StockLevel;

import java.util.ArrayList;
import java.util.List;

public class StockRepository {
    private static StockRepository instance;
    private List<StockLevel> listOfStockLevels = new ArrayList<>();

    private StockRepository(){

    }

    public static StockRepository getInstance(){
        if(instance == null){
            instance = new StockRepository();
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
