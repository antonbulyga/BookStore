package main.java.com.senla.view.actions;

import main.java.com.senla.model.utils.WriteObject;
import main.java.com.senla.view.api.IAction;

import java.io.IOException;

public class ActionExit implements IAction {
    @Override
    public void execute() throws IOException {
        WriteObject.write();
        System.exit(0);
    }
}
