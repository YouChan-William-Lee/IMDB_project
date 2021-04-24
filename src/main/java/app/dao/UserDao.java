package app.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.model.Users.*;

public class UserDao {

    private List<User> regularUsers = new ArrayList<User>(Arrays.asList(
            //        Username    Salt for hash                    Hashed password (the password is "password" for all users)
            new RegularUser("regular1", "$2a$10$h.dl5J86rGH7I8bD9bZeZe", "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO", "Andrew", "Jason", "asdf@gmail.com", "Male","Australia"),
            new RegularUser("regular2", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy", "Emma", "Wilson", "qwer@gmail.com", "Female","Australia"),
            new RegularUser("regular3", "$2a$10$E3DgchtVry3qlYlzJCsyxe", "$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2", "William", "Liam", "zxcv@gmail.com", "Male","Australia")
    ));

    private List<User> adminUsers = new ArrayList<User>(Arrays.asList(
            //        Username    Salt for hash                    Hashed password (the password is "password" for all users)
            new AdminUser("admin", "$2a$10$h.dl5J86rGH7I8bD9bZeZe", "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO", "John", "Walker", "asdf123@gmail.com", "Male","Australia")
    ));

    private List<User> criticsUsers = new ArrayList<User>(Arrays.asList(
            //        Username    Salt for hash                    Hashed password (the password is "password" for all users)
            new CriticsUser("critics1", "$2a$10$h.dl5J86rGH7I8bD9bZeZe", "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO", "Sarah", "Wilson", "123adsf@gmail.com", "Female","Australia"),
            new CriticsUser("critics2", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy", "Bucky", "Barnes", "qwer123@gmail.com", "Male","Australia"),
            new CriticsUser("critics3", "$2a$10$E3DgchtVry3qlYlzJCsyxe", "$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2", "Barry", "Allen", "zxcv123@gmail.com", "Male","Australia")
    ));

    private List<User> PCOUsers = new ArrayList<User>(Arrays.asList(
            //        Username    Salt for hash                    Hashed password (the password is "password" for all users)
            new PCOUser("PCO1", "$2a$10$h.dl5J86rGH7I8bD9bZeZe", "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO", "Universal", "Pictures", "universalpictures@gmail.com", null, null),
            new PCOUser("PCO2", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy", "Paramount", "Pictures", "paramountpictures@gmail.com", null, null),
            new PCOUser("PCO3", "$2a$10$E3DgchtVry3qlYlzJCsyxe", "$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2", "Warner", "Bros.", "warnerbros@gmail.com", null, null)
    ));


    public User getUserByUsername(String username) {

        boolean foundUser = false;
        User user = null;

        for (int i = 0; i < regularUsers.size() && !foundUser; i++) {
            if (regularUsers.get(i).getUsername().equals(username)) {
                user = regularUsers.get(i);
                foundUser = true;
            }
        }

        for (int i = 0; i < criticsUsers.size() && !foundUser; i++) {
            if (criticsUsers.get(i).getUsername().equals(username)) {
                user = criticsUsers.get(i);
                foundUser = true;
            }
        }

        for (int i = 0; i < PCOUsers.size() && !foundUser; i++) {
            if (PCOUsers.get(i).getUsername().equals(username)) {
                user = PCOUsers.get(i);
                foundUser = true;
            }
        }

        for (int i = 0; i < adminUsers.size() && !foundUser; i++) {
            if (adminUsers.get(i).getUsername().equals(username)) {
                user = adminUsers.get(i);
                foundUser = true;
            }
        }
        return user;

//        return regularUsers.stream().filter(b -> b.username.equals(username)).findFirst().orElse(null);
    }

    public void addRegularUser(User user) {
        regularUsers.add(user);
    }

    public void addPCOUser(User user) {
        PCOUsers.add(user);
    }

    public void addCriticsUser(User user) {
        criticsUsers.add(user);
    }
//
//    public Iterable<String> getAllUserNames() {
//        return regularUsers.stream().map(user -> user.username).collect(Collectors.toList());
//    }
//
//    public Iterable<User> getAllUsers() {
//        return regularUsers;
//    }
//
//    public void updateUsersList(User user) {
//        regularUsers.add(user);
//    }
}
