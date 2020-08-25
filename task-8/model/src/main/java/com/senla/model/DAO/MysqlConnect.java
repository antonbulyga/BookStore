package main.java.com.senla.model.DAO;

import main.java.com.senla.config.annotations.MyInject;

import java.sql.*;

public class MysqlConnect {
    public Connection conn;
    private static MysqlConnect instance;
   /* @MyInject(key = "url")
    String url;
    @MyInject(key = "bookstore")
    String dbName;
    @MyInject(key = "com.mysql.cj.jdbc.Driver")
    String driver;
    @MyInject(key = "root")
    String userName;
    @MyInject(key = "405841ab")
    String password;*/
    private MysqlConnect() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "bookstore";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "405841ab";

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
