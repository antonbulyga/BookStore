package com.senla.view.actions;

import com.senla.model.сontrollers.BookController;
import com.senla.view.api.IAction;

public class ActionExportBook implements IAction {

    @Override
    public void execute() throws IllegalAccessException {
        BookController.getInstance().exportBook();
    }
}
