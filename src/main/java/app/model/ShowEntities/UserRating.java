package app.model.ShowEntities;

import java.util.Date;


public class UserRating {
    private final int showId;
    private final String userId;
    private final int rating;
    private final String date;

    public UserRating(int showId, String userId, int rating, String date) {
        this.showId = showId;
        this.userId = userId;
        this.rating = rating;
        this.date = date;
    }

    public int getShowId() {
        return showId;
    }

    public String getUserId() {
        return userId;
    }

    public int getRating() {
        return rating;
    }

    public String getDate() {
        return date;
    }
}
