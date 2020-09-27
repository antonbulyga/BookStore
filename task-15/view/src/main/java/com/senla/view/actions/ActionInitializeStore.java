package com.senla.view.actions;

import com.senla.model.utils.BeanGetter;
import com.senla.view.api.IAction;

import java.io.IOException;

public class ActionInitializeStore implements IAction {
    @Override
    public void execute() throws IOException {
        BeanGetter.getInstance().getReadObjectBean().read();
    }
}
