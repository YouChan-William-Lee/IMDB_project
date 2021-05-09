package app.model.Users;

public class CriticsUser extends User{
    public CriticsUser(String username, String salt, String hashedPassword, String firstname, String lastname, String email, String gender, String country, String typeOfUser, boolean approved) {
        super(username, salt, hashedPassword, firstname, lastname, email, gender, country, typeOfUser, approved);
    }
}
