package app.dao;

import java.sql.*;

public class Model {

    protected static Connection connection;

    public static void connectToDatabase(String url, String user, String password) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT * FROM person");
//            rs.next();
//            System.out.println(rs.getString(2));
//            rs.next();
//            System.out.println(rs.getString(2));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
