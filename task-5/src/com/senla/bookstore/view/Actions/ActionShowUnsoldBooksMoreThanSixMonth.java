package com.senla.bookstore.view.actions;

import com.senla.bookstore.model.сontrollers.StoreController;
import com.senla.bookstore.model.service.StoreService;
import com.senla.bookstore.view.api.IAction;


public class ActionShowUnsoldBooksMoreThanSixMonth implements IAction {
    @Override
    public void execute() {
        StoreController.getInstance().showUnsoldBooksMoreThanSixMonth(StoreService.getInstance().getStore());
    }
}
