package app.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.model.Users.*;
import app.controller.UserController;

public class UserDao extends DatabaseDao{

    private List<User> regularUsers;
    private List<User> adminUsers;
    private List<User> criticsUsers;
    private List<User> PCOUsers;

    public UserDao() {
        regularUsers = new ArrayList<User>(Arrays.asList(
                //        Username    Salt for hash                    Hashed password (the password is "password" for all users)
                new RegularUser("regular1", "$2a$10$h.dl5J86rGH7I8bD9bZeZe", "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO", "Andrew", "Jason", "asdf@gmail.com", "Male","Australia"),
                new RegularUser("regular2", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy", "Emma", "Wilson", "qwer@gmail.com", "Female","Australia"),
                new RegularUser("regular3", "$2a$10$E3DgchtVry3qlYlzJCsyxe", "$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2", "William", "Liam", "zxcv@gmail.com", "Male","Australia")
        ));

        adminUsers = new ArrayList<User>(Arrays.asList(
                //        Username    Salt for hash                    Hashed password (the password is "password" for all users)
                new AdminUser("admin", "$2a$10$h.dl5J86rGH7I8bD9bZeZe", "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO", "John", "Walker", "asdf123@gmail.com", "Male","Australia")
        ));

        criticsUsers = new ArrayList<User>(Arrays.asList(
                //        Username    Salt for hash                    Hashed password (the password is "password" for all users)
                new CriticsUser("critics1", "$2a$10$h.dl5J86rGH7I8bD9bZeZe", "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO", "Sarah", "Wilson", "123adsf@gmail.com", "Female","Australia"),
                new CriticsUser("critics2", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy", "Bucky", "Barnes", "qwer123@gmail.com", "Male","Australia"),
                new CriticsUser("critics3", "$2a$10$E3DgchtVry3qlYlzJCsyxe", "$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2", "Barry", "Allen", "zxcv123@gmail.com", "Male","Australia")
        ));

        PCOUsers = new ArrayList<User>(Arrays.asList(
                //        Username    Salt for hash                    Hashed password (the password is "password" for all users)
                new PCOUser("universalpictures", "$2a$10$h.dl5J86rGH7I8bD9bZeZe", "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO", "Universal", "Pictures", "universalpictures@gmail.com"),
                new PCOUser("paramountpictures", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy", "Paramount", "Pictures", "paramountpictures@gmail.com"),
                new PCOUser("warnerbros", "$2a$10$E3DgchtVry3qlYlzJCsyxe", "$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2", "Warner", "Bros.", "warnerbros@gmail.com")
        ));
    }

    public boolean addRegularUserToDatabase(User user){
        String sql= "insert into account(username,salt,password,email,country,gender,first_name,last_name,type_of_user) values(?,?,?,?,?,?,?,?,?)" ;
		try {
            Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getSalt());
			preparedStatement.setString(3, user.getHashedPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getCountry());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getFirstname());
            preparedStatement.setString(8, user.getLastname());
            preparedStatement.setString(9, "regular_user");
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
    }

    public boolean addPCOUserToDatabase(User user){
        String sql= "insert into account(username,salt,password,email,country,gender,first_name,last_name,type_of_user) values(?,?,?,?,null,null,?,?,?)" ;
		try {
            Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getSalt());
			preparedStatement.setString(3, user.getHashedPassword());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getFirstname());
            preparedStatement.setString(6, user.getLastname());
            preparedStatement.setString(7, "pco_user");
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
    }

    public boolean addCriticsUserToDatabase(User user){
        String sql= "insert into account(username,salt,password,email,country,gender,first_name,last_name,type_of_user) values(?,?,?,?,?,?,?,?,?)" ;
		try {
            Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getSalt());
			preparedStatement.setString(3, user.getHashedPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getCountry());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getFirstname());
            preparedStatement.setString(8, user.getLastname());
            preparedStatement.setString(9, "critics_user");
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
    }

    public boolean addAdminUserToDatabase(User user){
        String sql= "insert into account(username,salt,password,email,country,gender,first_name,last_name,type_of_user) values(?,?,?,?,?,?,?,?,?)" ;
		try {
            Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getSalt());
			preparedStatement.setString(3, user.getHashedPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getCountry());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getFirstname());
            preparedStatement.setString(8, user.getLastname());
            preparedStatement.setString(9, "admin_user");
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
    }

    public void initializeDatabase(){
        for (User user : PCOUsers) {
            if(UserController.duplicationCheck(user.getUsername())){
                addPCOUserToDatabase(user);
                System.out.println(user.getUsername()+" has been added in databese");
            }
        }
        for (User user : regularUsers) {
            if(UserController.duplicationCheck(user.getUsername())){
                addRegularUserToDatabase(user);
                System.out.println(user.getUsername()+" has been added in databese");
            }
        }
        for (User user : adminUsers) {
            if(UserController.duplicationCheck(user.getUsername())){
                addAdminUserToDatabase(user);
                System.out.println(user.getUsername()+" has been added in databese");
            }
        }
        for (User user : criticsUsers) {
            if(UserController.duplicationCheck(user.getUsername())){
                addCriticsUserToDatabase(user);
                System.out.println(user.getUsername()+" has been added in databese");
            }
        }
    }

    public User getUserByUsername(String username) {

        User user = null;
        String sql;

        try{
            sql = "select * from account where username=?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                user = new User(rs.getString("username"), rs.getString("salt"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("gender"), rs.getString("country"));
            }
            return user;
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return user;

//        return regularUsers.stream().filter(b -> b.username.equals(username)).findFirst().orElse(null);
    }

    //add user to regularUsers (the ArrayList, not databse)
    public void addRegularUser(User user) {
        regularUsers.add(user);
    }

    //add user to PCOUsers (the ArrayList, not database)
    public void addPCOUser(User user) {
        PCOUsers.add(user);
    }

    //add user to criticsUsers (the ArrayList, not databse)
    public void addCriticsUser(User user) {
        criticsUsers.add(user);
    }
//
//    public Iterable<String> getAllUserNames() {
//        return regularUsers.stream().map(user -> user.username).collect(Collectors.toList());
//    }
//
    public Iterable<User> getAllUsers() {
        List<User> AllUsers = new ArrayList<User>();

        for (int i = 0; i < regularUsers.size(); i++) {
            AllUsers.add(regularUsers.get(i));
        }

        for (int i = 0; i < criticsUsers.size(); i++) {
            AllUsers.add(criticsUsers.get(i));
        }

        for (int i = 0; i < PCOUsers.size(); i++) {
            AllUsers.add(PCOUsers.get(i));
        }

        for (int i = 0; i < adminUsers.size(); i++) {
            AllUsers.add(adminUsers.get(i));
        }

        return AllUsers;
    }
//
//    public void updateUsersList(User user) {
//        regularUsers.add(user);
//    }
}
