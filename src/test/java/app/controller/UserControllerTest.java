package app.controller;

import app.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static app.controller.LoginController.approvedCheck;
import static app.controller.UserController.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @BeforeAll
    static void initialise() {
        Main.initialization();
    }

    @Test
    void authenticateAdmin() {
        assertEquals(true, authenticate("admin", "password"));
    }

    @Test
    void authenticateRegular1() {
        assertEquals(true, authenticate("regular1", "password"));
    }

    @Test
    void authenticateRegular2() {
        assertEquals(true, authenticate("regular2", "password"));
    }

    @Test
    void authenticateCritics1() {
        assertEquals(true, authenticate("critics1", "password"));
    }

    @Test
    void authenticateCritics2() {
        assertEquals(true, authenticate("critics2", "password"));
    }

    @Test
    void authenticatePCO1() {
        assertEquals(true, authenticate("pco1", "password"));
    }

    @Test
    void authenticatePCO2() {
        assertEquals(true, authenticate("pco2", "password"));
    }

    @Test
    void authenticateNonexistentUser() {
        assertEquals(false, authenticate("admin2", "password"));
    }

    @Test
    void authenticateAdminWithWrongPassword() {
        assertEquals(false, authenticate("admin", "PASSWORD"));
    }

    @Test
    void authenticateNullValue() {
        assertEquals(false, authenticate(null, null));
    }

    @Test
    void authenticateEmptyString() {
        assertEquals(false, authenticate("", ""));
    }

}