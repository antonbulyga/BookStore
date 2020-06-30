package main.java.com.senla.model.сontrollers;

import main.java.com.senla.model.entity.Book;
import main.java.com.senla.model.entity.Order;
import main.java.com.senla.model.entity.Store;
import main.java.com.senla.model.service.StoreService;

import java.time.LocalDate;
import java.util.List;


public class StoreController {
    private static StoreController instance;

    private StoreController(){

    }

    public static StoreController getInstance(){
        if(instance == null){
            instance = new StoreController();
        }
        return instance;
    }

    public void sumOfMoneyPerPeriodOfTime(List<Order> orders , LocalDate date1, LocalDate date2) {
        StoreService.getInstance().sumOfMoneyPerPeriodOfTime(orders, date1, date2);
    }

    public void addOrderToStore(Order order){
        StoreService.getInstance().addOrderToStore(order);
    }

    public void arriveBookToStock(Book book){
         StoreService.getInstance().arriveBookToStock(book);
    }

    public void showUnsoldBooksMoreThanSixMonth(Store store){
        StoreService.getInstance().showUnsoldBooksMoreThanSixMonth(store);
    }

    public void executeOrder(Order order){
        StoreService.getInstance().executeOrder(order);
    }

    public void completingRequestAfterArrivingNewBook(Book book) {
        StoreService.getInstance().completingRequestAfterArrivingNewBook(book);
    }

}


