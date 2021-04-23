package app.model;

public class User {
    public final String username;
    public String salt;
    public String hashedPassword;
    public String firstname;
    public String lastname;
    public String email;
    public String gender;
    public String country;

    public User(String username, String salt, String hashedPassword, String firstname, String lastname, String email, String gender, String country) {
        this.username = username;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.country = country;
    }

    public void updateUserInfo(String firstname, String lastname, String email, String gender, String country) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.country = country;
    }

    public String getUsername() { return username; }

    public String getFirstname() { return firstname; }

    public String getLastname() { return lastname; }

    public String getEmail() { return email; }

    public String getGender() { return gender; }

    public String getCountry() { return country; }
}

