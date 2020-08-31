package com.senla.model.DAO;

import com.senla.config.PropertyData;

import java.sql.Connection;
import java.sql.DriverManager;


public class MysqlConnect {
    private String url = PropertyData.getProperty("url", "config/src/main/java/com/senla/config/resources/config.properties");
    private String dbName = PropertyData.getProperty("dbName", "config/src/main/java/com/senla/config/resources/config.properties");
    private String driver = PropertyData.getProperty("driver", "config/src/main/java/com/senla/config/resources/config.properties");
    private String userName = PropertyData.getProperty("userName", "config/src/main/java/com/senla/config/resources/config.properties");
    private String password = PropertyData.getProperty("password", "config/src/main/java/com/senla/config/resources/config.properties");
    public Connection conn;
    private static MysqlConnect instance;
    private MysqlConnect() {
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    public static MysqlConnect getInstance() {
        if ( instance == null ) {
            instance = new MysqlConnect();
        }
        return instance;

    }

}
