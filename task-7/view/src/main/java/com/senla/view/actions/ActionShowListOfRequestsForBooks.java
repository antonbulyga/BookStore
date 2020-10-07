package main.java.com.senla.view.actions;

import main.java.com.senla.model.сontrollers.RequestForBookController;
import main.java.com.senla.view.api.IAction;

public class ActionShowListOfRequestsForBooks implements IAction {

    @Override
    public void execute() {
        RequestForBookController.getInstance().showListOfRequestsForBooks();
    }
}
