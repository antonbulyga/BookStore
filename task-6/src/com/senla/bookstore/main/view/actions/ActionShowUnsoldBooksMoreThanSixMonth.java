package com.senla.bookstore.main.view.actions;

import com.senla.bookstore.main.model.сontrollers.StoreController;
import com.senla.bookstore.main.model.service.StoreService;
import com.senla.bookstore.main.view.api.IAction;


public class ActionShowUnsoldBooksMoreThanSixMonth implements IAction {
    @Override
    public void execute() {
        StoreController.getInstance().showUnsoldBooksMoreThanSixMonth(StoreService.getInstance().getStore());
    }
}
