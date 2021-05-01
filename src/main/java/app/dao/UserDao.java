package app.dao;

import java.util.ArrayList;
import java.util.List;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.model.Users.*;

public class UserDao extends Database {

    public void addUserToDatabase(User user) { // Adding users onto the DB.
        String sql= "insert into account(username, salt, password, email, country, gender, first_name, last_name, type_of_user) values(?,?,?,?,?,?,?,?,?)" ;
        try {
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getSalt());
            preparedStatement.setString(3, user.getHashedPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getCountry());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getFirstname());
            preparedStatement.setString(8, user.getLastname());
            preparedStatement.setString(9, user.getTypeOfUser());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsername(String username) { // Obtaining user via name searching

        User user = null;
        String sql;

        try{
            sql = "select * from account where username=?";
            setPreparedStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                user = new User(rs.getString("username"), rs.getString("salt"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("gender"), rs.getString("country"), rs.getString("type_of_user"));
            }
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return user;
    }

    public Iterable<User> getAllUsers() {
        List<User> allUsers = new ArrayList<User>();

        String sql;
        try{
            sql = "select * from account";
            setPreparedStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (true) {
                if (rs.next()) {
                    allUsers.add(new User(rs.getString("username"), rs.getString("salt"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("gender"), rs.getString("country"), rs.getString("type_of_user")));

                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }
}
