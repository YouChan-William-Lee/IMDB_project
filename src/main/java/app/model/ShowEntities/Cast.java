package app.model.ShowEntities;

import java.sql.Date;

public class Cast {

    private final int id;
    private final String name;
    private final Date birthdate;
    private final String bio;
    private final String role;

    //Cast constructor
    public Cast(int id, String name, String role, Date birthdate, String bio) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.bio = bio;
        this.birthdate = birthdate;
    }

    public int getID() { return id; }
    public String getName() {
        return name;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public String getBio() {
        return bio;
    }
    public String getRole() {
        return role;
    }

}
