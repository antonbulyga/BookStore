package com.senla.model.DAO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class MysqlConnect {
    private static Connection conn = null;
    @Value("${url}")
    private String url;
    @Value("${dbName}")
    private String dbName;
    @Value("${driver}")
    private String driver;
    @Value("${userName.name}")
    private String userName;
    @Value("${password}")
    private String password;

    public Connection getConnection() {
        if(conn != null){
            return conn;
        }
        return getConnection(url,dbName,userName,password);
    }

    public static MysqlConnect getMySqlConnectBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MysqlConnect mysqlConnect = context.getBean(MysqlConnect.class);
        return mysqlConnect;
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

}
