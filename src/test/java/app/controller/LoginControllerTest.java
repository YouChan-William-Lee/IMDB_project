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
    }

    @Test
    void approvedCheckRegular1() {
        assertEquals(true, approvedCheck("regular1"));
    }

    @Test
    void approvedCheckRegular2() {
        assertEquals(true, approvedCheck("regular2"));
    }

    @Test
    void approvedCheckCritics1() {
        assertEquals(true, approvedCheck("critics1"));
    }

    @Test
    void approvedCheckCritics2() {
        assertEquals(true, approvedCheck("critics2"));
    }

    @Test
    void approvedCheckPCO1() {
        assertEquals(false, approvedCheck("pco1"));
    }

    @Test
    void approvedCheckPCO2() {
        assertEquals(true, approvedCheck("pco2"));
    }

    @Test
    void approvedCheckNonexistentUser() {
        assertThrows(
                NullPointerException.class,
                () -> approvedCheck("admin2"),
                "Non-existent username should throw (NullPointerException)");
    }

    @Test
    void approvedCheckEmptyString() {
        assertThrows(
                NullPointerException.class,
                () -> approvedCheck(""),
                "Non-existent username should throw (NullPointerException)");
    }

    @Test
    void approvedCheckNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> approvedCheck(null),
                "Invalid null character should throw (NullPointerException)");
    }

}