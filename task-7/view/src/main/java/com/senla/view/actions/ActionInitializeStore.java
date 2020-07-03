package main.java.com.senla.view.actions;

import main.java.com.senla.model.utils.ReadObject;
import main.java.com.senla.view.api.IAction;

import java.io.IOException;

public class ActionInitializeStore implements IAction {
    @Override
    public void execute() throws IOException {
        ReadObject.read();
    }
}
