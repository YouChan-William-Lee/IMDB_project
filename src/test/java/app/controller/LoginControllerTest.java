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
        assertTrue(approvedCheck("admin"));
    }

    @Test
    void approvedCheckRegular1() {
        assertTrue(approvedCheck("regular1"));
    }

    @Test
    void approvedCheckRegular2() {
        assertTrue(approvedCheck("regular2"));
    }

    @Test
    void approvedCheckCritics1() {
        assertTrue(approvedCheck("critics1"));
    }

    @Test
    void approvedCheckCritics2() {
        assertTrue(approvedCheck("critics2"));
    }

    @Test
    void approvedCheckPCO1() {
        assertFalse(approvedCheck("pco1"));
    }

    @Test
    void approvedCheckPCO2() {
        assertTrue(approvedCheck("pco2"));
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
                "Empty string should throw (NullPointerException)");
    }

    @Test
    void approvedCheckNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> approvedCheck(null),
                "Invalid null character should throw (NullPointerException)");
    }

}