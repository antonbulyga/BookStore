package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.Store;
import com.senla.bookstore.service.StoreService;
import com.senla.bookstore.view.IAction;


public class ActionShowUnsoldBooksMoreThanSixMonth implements IAction {
    Store store = new Store();
    StoreService storeService = new StoreService();

    @Override
    public void execute() {
        storeService.showUnsoldBooksMoreThanSixMonth(store);
    }
}
