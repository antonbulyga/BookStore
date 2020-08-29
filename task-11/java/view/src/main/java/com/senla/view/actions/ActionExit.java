package main.java.com.senla.view.actions;

import main.java.com.senla.model.DAO.MysqlConnect;
import main.java.com.senla.model.utils.WriteObject;
import main.java.com.senla.view.api.IAction;

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
