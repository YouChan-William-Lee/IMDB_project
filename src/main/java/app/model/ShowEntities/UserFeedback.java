package app.model.ShowEntities;

import java.util.Date;


public class UserFeedback {
    private final int showId;
    private final String userId;
    private final String feedback;
    private final String date;

    public UserFeedback(int showId, String userId, String feedback, String date) {
        this.showId = showId;
        this.userId = userId;
        this.feedback = feedback;
        this.date = date;
    }

    public int getShowId() {
        return showId;
    }

    public String getUserId() {
        return userId;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getDate() {
        return date;
    }
}
