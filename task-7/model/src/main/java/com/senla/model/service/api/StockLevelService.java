package main.java.com.senla.model.service.api;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.StockLevel;

import java.util.List;

public interface StockLevelService {
    List<StockLevel> getListOfStockLevels();
    void stockLevelsUpdate(Book book);
    public void setListOfStockLevels(List<StockLevel> stockLevels);
    public void setArrayOfStockLevels(List<StockLevel> stockLevels);
}
