package app.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import app.model.Show;
import app.model.User;
import com.google.common.collect.ImmutableList;

public class UserDao {

    private List<User> users = new ArrayList<User>(Arrays.asList(
            //        Username    Salt for hash                    Hashed password (the password is "password" for all users)
            new User("perwendel", "$2a$10$h.dl5J86rGH7I8bD9bZeZe", "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO", "Andrew", "Jason", "asdf@gmail.com", "Male", "RegularUser" ,"Australia"),
            new User("davidase", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy", "Emma", "Wilson", "qwer@gmail.com", "Female", "RegularUser","Australia"),
            new User("federico", "$2a$10$E3DgchtVry3qlYlzJCsyxe", "$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2", "William", "Liam", "zxcv@gmail.com", "Male", "PCoUser","Australia")
    ));

    public User getUserByUsername(String username) {
        return users.stream().filter(b -> b.username.equals(username)).findFirst().orElse(null);
    }

    public Iterable<String> getAllUserNames() {
        return users.stream().map(user -> user.username).collect(Collectors.toList());
    }

    public Iterable<User> getAllUsers() {
        return users;
    }

    public void updateUsersList(User user) {
        users.add(user);
    }
}
