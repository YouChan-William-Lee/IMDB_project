package app.dao;

import java.sql.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import com.google.thirdparty.publicsuffix.PublicSuffixPatterns;

import app.controller.paths.SQL;

public class DatabaseDao {

    protected static Connection connection;

    //connect to database and return the connection
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(SQL.url, SQL.dbUser, SQL.dbPassword);
            System.out.println("successfully connect to database: "+SQL.url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //database disconnect
    public static void closeConnection(){
        if(connection != null){
            try{
                connection.close();
                System.out.println("disconnect to database");
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

}
