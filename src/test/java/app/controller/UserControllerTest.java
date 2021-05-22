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
        assertTrue(authenticate("admin", "password"));
    }

    @Test
    void authenticateRegular1() {
        assertTrue(authenticate("regular1", "password"));
    }

    @Test
    void authenticateRegular2() {
        assertTrue(authenticate("regular2", "password"));
    }

    @Test
    void authenticateCritics1() {
        assertTrue(authenticate("critics1", "password"));
    }

    @Test
    void authenticateCritics2() {
        assertTrue(authenticate("critics2", "password"));
    }

    @Test
    void authenticatePCO1() {
        assertTrue(authenticate("pco1", "password"));
    }

    @Test
    void authenticatePCO2() {
        assertTrue(authenticate("pco2", "password"));
    }

    @Test
    void authenticateNonexistentUser() {
        assertFalse(authenticate("admin2", "password"));
    }

    @Test
    void authenticateAdminWithWrongPassword() {
        assertFalse(authenticate("admin", "PASSWORD"));
    }

    @Test
    void authenticateNullValue() {
        assertFalse(authenticate(null, null));
    }

    @Test
    void authenticateEmptyString() {
        assertFalse(authenticate("", ""));
    }

}