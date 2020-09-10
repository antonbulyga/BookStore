package com.senla.view.actions;

import com.senla.model.DAO.MysqlConnect;
import com.senla.model.utils.WriteObject;
import com.senla.view.api.IAction;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class ActionExit implements IAction {
    static final Logger logger = Logger.getLogger(ActionExit.class);
    @Override
    public void execute() throws IOException {
        WriteObject.write();
        if(MysqlConnect.getInstance() != null){
            try {
                MysqlConnect.getInstance().getConnection().close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        System.exit(0);
    }
}
