package app.dao;

import java.util.ArrayList;
import java.util.List;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.model.Users.*;

public class UserDao extends Database {

    public void addUserToDatabase(User user) {
        // Add this user into database.
        String sql= "insert into account(username, salt, password, email, country, gender, first_name, last_name, type_of_user, approved) values(?,?,?,?,?,?,?,?,?,?)" ;
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
            preparedStatement.setBoolean(10, user.getApproved());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserToDatabase(User user) {
        // Add this user into database.
        String sql= "update account SET username=?, salt=?, password=?, email=?, country=?, gender=?, first_name=?, last_name=?, type_of_user=?, approved=? where username=?";
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
            preparedStatement.setBoolean(10, user.getApproved());
            preparedStatement.setString(11, user.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Delete user from database
    public void deleteUserToDatabase(User user) {
        // Add this user into database.
        String sql= "delete from account where username=?";
        try {
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Get the user by user name
    public User getUserByUsername(String username) { // Obtaining user via name searching
        User user = null;
        String sql;

        try{
            sql = "select * from account where username=?";
            setPreparedStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                user = new User(rs.getString("username"), rs.getString("salt"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("gender"), rs.getString("country"), rs.getString("type_of_user"), rs.getBoolean("approved"));
            }
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return user;
    }

    //Get all the users
    public Iterable<User> getAllUsers() {
        List<User> allUsers = new ArrayList<User>();

        String sql;
        try{
            sql = "select * from account";
            setPreparedStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (true) {
                if (rs.next()) {
                    allUsers.add(new User(rs.getString("username"), rs.getString("salt"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("gender"), rs.getString("country"), rs.getString("type_of_user"), rs.getBoolean("approved")));

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
