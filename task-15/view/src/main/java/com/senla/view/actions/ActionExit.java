package com.senla.view.actions;

import com.senla.model.DAO.MysqlConnect;
import com.senla.view.api.IAction;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class ActionExit implements IAction {
    static final Logger logger = Logger.getLogger(ActionExit.class);
    @Override
    public void execute() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MysqlConnect mysqlConnect = context.getBean(MysqlConnect.class);
        if(mysqlConnect != null){
            try {
                mysqlConnect.getConnection().close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        System.exit(0);
    }
}
