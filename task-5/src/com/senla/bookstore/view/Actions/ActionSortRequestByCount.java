package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.Store;
import com.senla.bookstore.service.RequestForBookService;
import com.senla.bookstore.view.IAction;

public class ActionSortRequestByCount implements IAction {
    Store store = new Store();
    RequestForBookService requestForBookService = new RequestForBookService();

    @Override
    public void execute() {
        requestForBookService.sortRequestByCount(store);
    }
}
