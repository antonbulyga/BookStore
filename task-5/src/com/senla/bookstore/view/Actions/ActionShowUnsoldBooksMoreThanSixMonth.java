package com.senla.bookstore.view.Actions;

import com.senla.bookstore.model.сontrollers.StoreController;
import com.senla.bookstore.service.StoreService;
import com.senla.bookstore.view.IAction;


public class ActionShowUnsoldBooksMoreThanSixMonth implements IAction {
    @Override
    public void execute() {
        StoreController.getInstance().showUnsoldBooksMoreThanSixMonth(StoreService.getInstance().getStore());
    }
}
