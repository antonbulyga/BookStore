package com.senla.view.actions;

import com.senla.model.utils.BeanGetter;
import com.senla.view.api.IAction;


public class ActionSortBookByDateArrive implements IAction {

    @Override
    public void execute() {
        BeanGetter.getInstance().getBookControllerBean().sortBookByDateArrive();
    }
}
