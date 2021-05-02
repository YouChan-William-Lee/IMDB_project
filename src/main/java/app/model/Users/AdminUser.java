package app.model.Users;

public class AdminUser extends User{
    public AdminUser(String username, String salt, String hashedPassword, String firstname, String lastname, String email, String gender, String country, String typeOfUser) {
        super(username, salt, hashedPassword, firstname, lastname, email, gender, country, typeOfUser);
    }
}
