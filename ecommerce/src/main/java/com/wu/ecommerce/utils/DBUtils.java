package com.wu.ecommerce.utils;
 
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
 
public class DBUtils {
	public static void main(String[] args) {
        
        Connection connection;
        try {
            connection = getInstance().getConnection();
            System.out.println("Connection successful: " + (connection != null));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    private DBUtils() {
        // Private constructor to enforce singleton pattern
    }
 
    private static DBUtils dbUtils;
 
    public static DBUtils getInstance() {
        if (dbUtils == null) {
            dbUtils = new DBUtils();
        }
        return dbUtils;
    }
 
    public Connection getConnection() throws SQLException {
        
    	Properties properties = loadProperties();
        System.out.println("Connection class "+properties);
        System.out.println(properties.getProperty("jdbc.url"));
       
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        Connection connection = DriverManager.getConnection(properties.getProperty("jdbc.url"), 
//        		properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "hr", "hr");
        return connection;
    }
 
    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    private Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream == null) {
                System.out.println("Properties file not found.");
                return properties;
            }
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Exception caught while loading properties: " + e.getMessage());
        }
        return properties;
    }
 
   
}