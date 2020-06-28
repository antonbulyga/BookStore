package main.java.com.senla.view.actions;

import main.java.com.senla.model.сontrollers.StoreController;
import main.java.com.senla.model.service.StoreService;
import main.java.com.senla.view.api.IAction;


public class ActionShowUnsoldBooksMoreThanSixMonth implements IAction {
    @Override
    public void execute() {
        StoreController.getInstance().showUnsoldBooksMoreThanSixMonth(StoreService.getInstance().getStore());
    }
}
