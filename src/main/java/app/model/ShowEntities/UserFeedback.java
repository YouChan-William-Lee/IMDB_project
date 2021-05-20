package app.model.ShowEntities;

import java.util.Date;


public class UserFeedback {
    private final int showId;
    private final String username;
    private final String feedback;
    private final String date;

    public UserFeedback(int showId, String username, String feedback, String date) {
        this.showId = showId;
        this.username = username;
        this.feedback = feedback;
        this.date = date;
    }

    public int getShowId() {
        return showId;
    }

    public String getUsername() {
        return username;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getDate() {
        return date;
    }
}
