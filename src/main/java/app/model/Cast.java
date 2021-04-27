package app.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Cast {

    private final int id;
    private final String name;
    private final Date birthdate;
    private final String bio;
    private final String role;

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
