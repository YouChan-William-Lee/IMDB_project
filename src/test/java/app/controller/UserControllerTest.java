package app.controller;

import app.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static app.controller.UserController.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @BeforeAll
    static void initialise() {
        Main.initialization();
    }

    @Test
    void authenticateAdmin() {
        assertEquals(true, authenticate("regular1", "password"));
//        Couldn't fix NullPointerException on userDao in UserController.java
    }

}