package app.model.Users;

import app.dao.UserDao;

public class PCOUser extends User {
    public PCOUser(String username, String salt, String hashedPassword, String firstname, String lastname, String email, String typeOfUser) {
        super(username, salt, hashedPassword, firstname, lastname, email, null, null, typeOfUser);
    }
}
