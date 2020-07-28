package main.java.com.senla.model.utils.generators;

public class StockLevelIdGenerator {
    private static int stockLevelId = 0;

    public static int getStockLevelId(){
        stockLevelId++;
        return stockLevelId;
    }
}
