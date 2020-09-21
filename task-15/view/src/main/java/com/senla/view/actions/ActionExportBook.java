package com.senla.view.actions;

import com.senla.model.utils.BeanGetter;
import com.senla.view.api.IAction;

public class ActionExportBook implements IAction {

    @Override
    public void execute() throws IllegalAccessException {

        BeanGetter.getInstance().getBookControllerBean().exportBook();
    }
}
