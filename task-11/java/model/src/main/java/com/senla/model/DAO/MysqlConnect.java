package com.senla.model.DAO;

import com.senla.config.annotations.MyInject;

import java.sql.Connection;
import java.sql.DriverManager;


public class MysqlConnect {
    @MyInject( key = "url")
    private String url;
    @MyInject( key = "dbName")
    private String dbName;
    @MyInject( key = "driver")
    private String driver;
    @MyInject( key = "userName")
    private String userName;
    @MyInject( key = "password")
    private String password;
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
