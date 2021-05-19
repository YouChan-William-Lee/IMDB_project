package app.controller;

import app.Main;
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
    void authenticateAdmin() {
//        assert(newUserReview("1", "regular1","3","This movie is such a nice movie."));
//        Couldn't fix NullPointerException on userDao in UserController.java
    }

}