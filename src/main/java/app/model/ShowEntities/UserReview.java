package app.model.ShowEntities;

import java.util.Date;


public class UserReview {
//    private final int reviewId;
    private final int showId;
    private final String userId;
    private final int rating;
    private final String review;
    private final String date;

    public UserReview(int showId, String userId, int rating, String review, String date) {
//        this.reviewId = reviewId;
        this.showId = showId;
        this.userId = userId;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public String getDate() {
        return date;
    }
}
