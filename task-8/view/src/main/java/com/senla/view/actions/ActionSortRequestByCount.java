package main.java.com.senla.view.actions;

import main.java.com.senla.model.сontrollers.RequestForBookController;
import main.java.com.senla.view.api.IAction;

public class ActionSortRequestByCount implements IAction {

    @Override
    public void execute() {
        RequestForBookController.getInstance().sortRequestByCount();
    }
}
