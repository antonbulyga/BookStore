package com.senla.view.api;

import java.io.IOException;
import java.sql.SQLException;

public interface IAction {
    public void execute() throws IOException, IllegalAccessException, SQLException;
}
