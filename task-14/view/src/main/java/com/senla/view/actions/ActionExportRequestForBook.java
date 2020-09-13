package com.senla.view.actions;

import com.senla.model.сontrollers.RequestForBookController;
import com.senla.view.api.IAction;

public class ActionExportRequestForBook implements IAction {

    @Override
    public void execute(){
        RequestForBookController.getRequestForBookControllerBean().exportRequestForBook();
    }
}
