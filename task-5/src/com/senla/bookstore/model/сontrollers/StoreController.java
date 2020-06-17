package com.senla.bookstore.model.сontrollers;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.Stock;
import com.senla.bookstore.model.Store;
import com.senla.bookstore.service.StoreService;


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


