package app.dao;

import java.sql.*;

import app.controller.paths.mySQL;

public class Database {

    protected static Connection connection;
    protected static PreparedStatement preparedStatement;

    //connect to database and return the connection
    public static Connection startConnection() {
        try {
            Class.forName(mySQL.dbDriver);
            connection = DriverManager.getConnection(mySQL.url, mySQL.dbUser, mySQL.dbPassword);
            System.out.println("successfully connect to database: "+ mySQL.url);
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

    public PreparedStatement setPreparedStatement(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return preparedStatement;
    }
}
