package app.model.ShowEntities;

import java.util.Date;


public class UserReview {
//    private final int reviewId;
    private final int showId;
    private final String userId;
    private final String review;
    private final String date;

    public UserReview(int showId, String userId, String review, String date) {
//        this.reviewId = reviewId;
        this.showId = showId;
        this.userId = userId;
        this.review = review;
        this.date = date;
    }

//    public int getReviewId() {
//        return reviewId;
//    }

    public int getShowId() {
        return showId;
    }

    public String getUserId() {
        return userId;
    }

    public String getReview() {
        return review;
    }

    public String getDate() {
        return date;
    }
}
