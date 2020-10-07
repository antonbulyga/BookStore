package main.java.com.senla.model.repository;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.model.entity.StockLevel;
import main.java.com.senla.model.repository.api.StockLevelRepository;

import java.util.ArrayList;
import java.util.List;
@Component
public class StockLevelRepositoryImpl implements StockLevelRepository {
    private List<StockLevel> listOfStockLevels = new ArrayList<>();

    public List<StockLevel> getListOfStockLevels() {
        return listOfStockLevels;
    }

    public void setListOfStockLevels(List<StockLevel> listOfStockLevels) {
        this.listOfStockLevels = listOfStockLevels;
    }
}
