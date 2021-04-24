package app.model.Users;

public class RegularUser extends User {


    public RegularUser(String username, String salt, String hashedPassword, String firstname, String lastname, String email, String gender, String country) {
        super(username, salt, hashedPassword, firstname, lastname, email, gender, country);
    }
}
