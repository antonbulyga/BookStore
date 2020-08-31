package main.java.com.senla.model.service;

import main.java.com.senla.config.annotations.Component;
import main.java.com.senla.config.annotations.MyAutoWired;
import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.StockLevel;
import main.java.com.senla.model.repository.api.StockLevelRepository;
import main.java.com.senla.model.service.api.BookService;
import main.java.com.senla.model.service.api.StockLevelService;

import java.util.List;
@Component
public class StockLevelServiceImpl implements StockLevelService {
    @MyAutoWired
    private StockLevelRepository stockLevelRepository;
    @MyAutoWired
    private BookService bookService;

    public List<StockLevel> getListOfStockLevels(){
        List<StockLevel> stockLevels = stockLevelRepository.getListOfStockLevels();
        return stockLevels;
    }

    public void stockLevelsUpdate(Book book) {
        List<StockLevel> stockLevels = stockLevelRepository.getListOfStockLevels();
        for (int i = 0; i < stockLevels.size(); i++) {
            if (stockLevels.get(i).getBook().getId() == book.getId()) {
                bookService.arriveBookToStock(book);
            }
        }
    }

    public void setListOfStockLevels(List<StockLevel> stockLevels){
        stockLevelRepository.setListOfStockLevels(stockLevels);
    }

    public void setArrayOfStockLevels(List<StockLevel> stockLevels){
        stockLevelRepository.setListOfStockLevels(stockLevels);
    }

    
}
