package app.model.Users;

public class PCOUser extends User {
    public PCOUser(String username, String salt, String hashedPassword, String firstname, String lastname, String email, String gender, String country) {
        super(username, salt, hashedPassword, firstname, lastname, email, gender, country);
    }
}
