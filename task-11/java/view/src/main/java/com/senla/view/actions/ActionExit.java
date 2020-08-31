package com.senla.view.actions;

import com.senla.model.DAO.MysqlConnect;
import com.senla.model.utils.WriteObject;
import com.senla.view.api.IAction;

import java.io.IOException;
import java.sql.SQLException;

public class ActionExit implements IAction {
    @Override
    public void execute() throws IOException {
        WriteObject.write();
        if(MysqlConnect.getInstance() != null){
            try {
                MysqlConnect.getInstance().conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
}
