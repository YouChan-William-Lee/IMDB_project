package app.model.Users;

public class User implements UserInterface {
    public final String username;
    public String salt;
    public String hashedPassword;
    public String firstname;
    public String lastname;
    public String email;
    public String gender;
//    public String typeofuser;
    public String country;

    public User(String username, String salt, String hashedPassword, String firstname, String lastname, String email, String gender, String country) {
        this.username = username;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
//        this.typeofuser = typeofuser;
        this.country = country;
    }

    public void updateUserInfo(String firstname, String lastname, String email, String gender,String typeofuser, String country) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
//        this.typeofuser = typeofuser;
        this.country = country;
    }

    public String getUsername() { return username; }

    public String getFirstname() { return firstname; }

    public String getLastname() { return lastname; }

    public String getEmail() { return email; }

    public String getGender() { return gender; }

//    public String getTypeofuser() {return typeofuser;}

    public String getCountry() { return country; }
}

