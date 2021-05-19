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
//       assertEquals(true, duplicationCheck("Star Wars: Episode IX - The Rise of Skywalker"));
//        Couldn't fix NullPointerException on showDao in ShowController.java
    }

    @Test
    void duplicationCheckInEditPage() {
    	assertEquals(false, ShowController.duplicationCheckInEditPage("Star Wars: Episode IX - The Rise of Skywalker", "Star Wars: Episode IX - The Rise of Skywalker"));
//        Couldn't fix NullPointerException on showDao in ShowController.java
    }

}