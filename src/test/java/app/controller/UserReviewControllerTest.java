package app.controller;

import app.Main;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static app.controller.UserReviewController.*;
import static org.junit.jupiter.api.Assertions.*;

class UserReviewControllerTest {

    @BeforeAll
    static void initialise() {
        Main.initialization();
    }

    @Test
    void newUserReviewRegualr1() {
        assertEquals(true, newUserReview("1", "regular1","3","This movie is such a nice movie."));
    }

    @Test
    void newUserReviewNullValueOnRating() {
        assertThrows(
                NumberFormatException.class,
                () -> newUserReview("1","regular1",null,"This movie is such a nice movie"),
                "Invalid null character should throw (NumberFormatException)");
    }

    @Test
    void newUserReviewNullValueOnShowID() {
        assertThrows(
                NumberFormatException.class,
                () -> newUserReview(null,"regular1","3","This movie is such a nice movie"),
                "Invalid null character should throw (NumberFormatException)");
    }

    @Test
    void newUserReviewNullValueOnUserID() {
        assertEquals(true, newUserReview("1",null,"3","This movie is such a nice movie"));
    }

    @Test
    void newUserReviewEmptyStringOnShowID() {
        assertThrows(
                NumberFormatException.class,
                () -> newUserReview("","regular1","3","This movie is such a nice movie"),
                "Invalid null character should throw (NumberFormatException)");
    }

    @Test
    void newUserReviewEmptyStringOnReview() {
        assertEquals(true, newUserReview("1","regular1","3",""));
    }
}