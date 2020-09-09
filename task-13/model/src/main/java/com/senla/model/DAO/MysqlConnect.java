package com.senla.model.DAO;

import com.senla.config.PropertyData;

import java.sql.Connection;
import java.sql.DriverManager;


public class MysqlConnect {
    private String url = PropertyData.getProperty("url", "model/src/main/resources/config.properties");
    private String dbName = PropertyData.getProperty("dbName", "model/src/main/resources/config.properties");
    private String driver = PropertyData.getProperty("driver", "model/src/main/resources/config.properties");
    private String userName = PropertyData.getProperty("userName", "model/src/main/resources/config.properties");
    private String password = PropertyData.getProperty("password", "model/src/main/resources/config.properties");
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
