package main.java.com.senla.model.repository.api;

import main.java.com.senla.model.entity.StockLevel;

import java.util.List;

public interface StockLevelRepository {
    List<StockLevel> getListOfStockLevels();

    void setListOfStockLevels(List<StockLevel> listOfStockLevels);

}
