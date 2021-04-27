package app.model.Users;

public class User implements UserInterface {
    private final String username;
    private String salt;
    private String hashedPassword;
    private String firstname;
    private String lastname;
    private String email;
    private String gender;
    private String country;
    private String typeOfUser;

    public User(String username, String salt, String hashedPassword, String firstname, String lastname, String email, String gender, String country, String typeOfUser) {
        this.username = username;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.typeOfUser = typeOfUser;
    }

    public void updateUserInfo(String firstname, String lastname, String email, String gender,String typeofuser, String country) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.country = country;
    }

    public String getUsername() { return username; }

    public String getSalt() { return salt; }

    public String getHashedPassword() { return hashedPassword; }

    public String getFirstname() { return firstname; }

    public String getLastname() { return lastname; }

    public String getEmail() { return email; }

    public String getGender() { return gender; }

    public String getCountry() { return country; }

    public String getTypeOfUser() {
        return typeOfUser;
    }
}

