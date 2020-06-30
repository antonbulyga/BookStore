package main.java.com.senla.model.service;

import main.java.com.senla.model.entity.StockLevel;
import main.java.com.senla.model.repository.StockRepository;

import java.util.List;

public class StockLevelService {
    private static StockLevelService instance;


    private StockLevelService() {

    }

    public static StockLevelService getInstance(){
        if(instance == null){
            instance = new StockLevelService();
        }
        return instance;
    }

    public List<StockLevel> getArrayOfStockLevels(){
        List<StockLevel> stockLevels = StockRepository.getInstance().getListOfStockLevels();
        return stockLevels;
    }

    public void setArrayOfStockLevels(List<StockLevel> stockLevels){
        StockRepository.getInstance().setListOfStockLevels(stockLevels);
    }

}
