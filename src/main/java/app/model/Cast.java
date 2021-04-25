package app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cast {

    private String name;
    private Date birthdate;
    private String bio;
    private List<Show> shows;

    public Cast(String name, Date birthdate, String bio) {
        this.name = name;
        this.bio = bio;
        this.birthdate = birthdate;
        this.shows = new ArrayList<Show>();
    }

    public String getName() {
        return name;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public String getBio() {
        return bio;
    }

    public List<Show> getAllShows() {
        return shows;
    }

    public void addShow(Show show) {
        shows.add(show);
    }

}
