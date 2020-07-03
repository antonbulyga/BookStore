package main.java.com.senla.model.utils.generators;

public class StockLevelIdGenerator {
    private static int StockLevelId = 0;

    public static int getStockLevelId(){
        StockLevelId++;
        return StockLevelId;
    }
}
