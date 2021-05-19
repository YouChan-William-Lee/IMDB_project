package app.controller;

import app.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static app.controller.ShowController.*;
import static org.junit.jupiter.api.Assertions.*;

class ShowControllerTest {

    @BeforeAll
    static void initialise() {
        Main.initialization();
    }

    @Test
    void duplicationCheckStarWarsEpisodeIX() {
       assertEquals(false, duplicationCheck("Star Wars: Episode IX - The Rise of Skywalker"));
    }

    @Test
    void duplicationCheckInception() {
        assertEquals(false, duplicationCheck("Inception"));
    }

    @Test
    void duplicationCheckAvengersEndgame() {
        assertEquals(false, duplicationCheck("Avengers: Endgame"));
    }

    @Test
    void duplicationCheckNonexistentShow() {
        assertEquals(true, duplicationCheck("Avengers: Infinitiy War"));
    }

    @Test
    void duplicationCheckNullValue() {
        assertEquals(false, duplicationCheck(null));
    }

    @Test
    void duplicationCheckEmptyString() {
        assertEquals(true, duplicationCheck(""));
    }

    @Test
    void duplicationCheckInEditPageStarWarsEpisodeIXwithSameShowTitle() {
    	assertEquals(true, ShowController.duplicationCheckInEditPage("Star Wars: Episode IX - The Rise of Skywalker", "Star Wars: Episode IX - The Rise of Skywalker"));
    }

    @Test
    void duplicationCheckInEditPageStarWarsEpisodeIXwithDifferentShowTitle() {
        assertEquals(true, ShowController.duplicationCheckInEditPage("Star Wars: Episode IX - The Rise of Skywalker 2", "Star Wars: Episode IX - The Rise of Skywalker"));
    }

    @Test
    void duplicationCheckInEditPageStarWarsEpisodeIXwithNullValue() {
        assertEquals(true, ShowController.duplicationCheckInEditPage(null, "Star Wars: Episode IX - The Rise of Skywalker"));
    }

    @Test
    void duplicationCheckInEditPageStarWarsEpisodeIXwithEmptyString() {
        assertEquals(true, ShowController.duplicationCheckInEditPage("", "Star Wars: Episode IX - The Rise of Skywalker"));
    }
}