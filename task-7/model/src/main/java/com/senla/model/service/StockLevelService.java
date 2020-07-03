package main.java.com.senla.model.service;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.StockLevel;
import main.java.com.senla.model.repository.StockLevelRepository;

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

    public List<StockLevel> getListOfStockLevels(){
        List<StockLevel> stockLevels = StockLevelRepository.getInstance().getListOfStockLevels();
        return stockLevels;
    }

    public void stockLevelsUpdate(Book book) {
        List<StockLevel> stockLevels = StockLevelRepository.getInstance().getListOfStockLevels();
        for (int i = 0; i < stockLevels.size(); i++) {
            if (stockLevels.get(i).getBook().getId() == book.getId()) {
                BookService.getInstance().arriveBookToStock(book);
            }
        }
    }

    public void setListOfStockLevels(List<StockLevel> stockLevels){
        StockLevelRepository.getInstance().setListOfStockLevels(stockLevels);
    }

    public void setArrayOfStockLevels(List<StockLevel> stockLevels){
        StockLevelRepository.getInstance().setListOfStockLevels(stockLevels);
    }

}
