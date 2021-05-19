package app.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static app.controller.LoginController.approvedCheck;
import app.Main;

class LoginControllerTest {

    @BeforeAll
    static void initialise() {
        Main.initialization();
    }

    @Test
    void approvedCheckAdmin() {
        assertEquals(true, approvedCheck("admin"));
//        Couldn't fix NullPointerException on userDao in LoginController.java
    }

//    @Test
//    void approvedCheckNullValue() {
//        assertThrows(
//                NullPointerException.class,
//                () -> approvedCheck(null),
//                "Invalid null character should throw (NullPointerException)");
//    }

}