package app.model.ShowEntities;

import java.util.Date;


public class UserRating {
    private final int showId;
    private final String username;
    private final int rating;
    private final String date;

    public UserRating(int showId, String username, int rating, String date) {
        this.showId = showId;
        this.username = username;
        this.rating = rating;
        this.date = date;
    }

    public int getShowId() {
        return showId;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

    public String getDate() {
        return date;
    }
}
