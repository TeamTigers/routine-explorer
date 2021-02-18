package com.FilesPack;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetConnectionClass {

    public PreparedStatement pst;

    public Connection getConnectiontoSqlite() throws UnsupportedEncodingException {
        try 
        {
            Connection connection = null;
            Class.forName("org.sqlite.JDBC");
            String ConnectionURL = "jdbc:sqlite:" + getPath() +"com/FilesPack/"+ "RoutineExample.sqlite";
            //String ConnectionURL = "jdbc:sqlite:${catalina.base}/webapps/package_name/WEB-INF/classes/RoutineExample.db";
            connection = DriverManager.getConnection(ConnectionURL);
            return connection;
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            System.out.println(e.toString());
            return null;
        }
    }

    public String getPath() throws UnsupportedEncodingException {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        return path;
    }
}
