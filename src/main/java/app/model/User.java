package app.model;

public class User {
    public final String username;
    public final String salt;
    public final String hashedPassword;
    public final String firstname;
    public final String lastname;
    public final String email;
    public final String gender;
    public final String country;

    public User(String username, String salt, String hashedPassword, String email, String country, String gender, String firstname, String lastname) {
        this.username = username;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.country = country;
        this.gender = gender;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}

