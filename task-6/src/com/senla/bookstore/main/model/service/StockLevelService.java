package com.senla.bookstore.main.model.service;

import com.senla.bookstore.main.model.entity.StockLevel;
import com.senla.bookstore.main.model.entity.Store;

import java.util.List;

public class StockLevelService {
    private static StockLevelService instance;
    private Store store;

    private StockLevelService() {
        store = StoreService.getInstance().getStore();
    }

    public static StockLevelService getInstance(){
        if(instance == null){
            instance = new StockLevelService();
        }
        return instance;
    }

    public List<StockLevel> getArrayOfStockLevels(){
        List<StockLevel> stockLevels = store.getStock().getArrayOfStockLevels();
        return stockLevels;
    }

    public void setArrayOfStockLevels(List<StockLevel> stockLevels){
        store.getStock().setArrayOfStockLevels(stockLevels);
    }

}
