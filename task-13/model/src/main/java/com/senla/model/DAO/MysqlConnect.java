package com.senla.model.DAO;

import com.senla.config.annotations.MyInject;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnect {
    private static Connection conn = null;
    private static MysqlConnect instance;
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

    public Connection getConnection() {
        if(conn != null){
            return conn;
        }
        return getConnection(url,dbName,userName,password);
    }

    private Connection getConnection(String url, String dbName, String userName, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
        return conn;
    }

    public static MysqlConnect getInstance() {
        if ( instance == null ) {
            instance = new MysqlConnect();
        }
        return instance;

    }

}
