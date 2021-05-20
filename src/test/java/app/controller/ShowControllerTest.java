package app.controller;

import app.Main;
import app.model.ShowEntities.Show;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static app.Main.showDao;
import static app.controller.LoginController.approvedCheck;
import static app.controller.ShowController.*;
import static org.junit.jupiter.api.Assertions.*;

class ShowControllerTest {

    @BeforeAll
    static void initialise() {
        Main.initialization();
    }

    @Test
    void duplicationCheckStarWarsEpisodeIX() {
       assertFalse(duplicationCheck("Star Wars: Episode IX - The Rise of Skywalker"));
    }

    @Test
    void duplicationCheckInception() {
        assertFalse(duplicationCheck("Inception"));
    }

    @Test
    void duplicationCheckAvengersEndgame() {
        assertFalse(duplicationCheck("Avengers: Endgame"));
    }

    @Test
    void duplicationCheckNonexistentShow() {
        assertTrue(duplicationCheck("Avengers: Infinitiy War"));
    }

    @Test
    void duplicationCheckNullValue() {
        assertFalse(duplicationCheck(null));
    }

    @Test
    void duplicationCheckEmptyString() {
        assertTrue(duplicationCheck(""));
    }

    @Test
    void duplicationCheckInEditPageStarWarsEpisodeIXwithSameShowTitle() {
    	assertTrue(ShowController.duplicationCheckInEditPage("Star Wars: Episode IX - The Rise of Skywalker", "Star Wars: Episode IX - The Rise of Skywalker"));
    }

    @Test
    void duplicationCheckInEditPageStarWarsEpisodeIXwithDifferentShowTitle() {
        assertTrue(ShowController.duplicationCheckInEditPage("Star Wars: Episode IX - The Rise of Skywalker 2", "Star Wars: Episode IX - The Rise of Skywalker"));
    }

    @Test
    void duplicationCheckInEditPageStarWarsEpisodeIXwithNullValue() {
        assertTrue(ShowController.duplicationCheckInEditPage(null, "Star Wars: Episode IX - The Rise of Skywalker"));
    }

    @Test
    void duplicationCheckInEditPageStarWarsEpisodeIXwithEmptyString() {
        assertTrue(ShowController.duplicationCheckInEditPage("", "Star Wars: Episode IX - The Rise of Skywalker"));
    }

    @NotNull
    private String[] getShowsByTitle(String searchedString) {
        List<String> str = new ArrayList<String>();
        for(Show show : ShowController.getSearchedShowsByShowTitles(searchedString)) {
            str.add(show.getShowTitle());
        }
        return str.toArray(new String[0]);
    }

    @NotNull
    private String[] getShowsByActor(String searchedString) {
        List<String> str = new ArrayList<String>();
        for(Show show : ShowController.getSearchedShowsByActors(searchedString)) {
            str.add(show.getShowTitle());
        }
        return str.toArray(new String[0]);
    }

    @Test
    void getSearchedShowsByShowTitlesWithStar() {
        String result[] = getShowsByTitle("star");
        String[] actual = new String[]{"Star Wars: Episode IX - The Rise of Skywalker",
                                        "Star Wars: Episode III - Revenge of The Sith",
                                        "Star Wars: Episode V - The Empire strikes Back",
                                        "Star Wars: Episode VII - The Force Awakens"};

        assertArrayEquals(actual, result);
    }

    @Test
    void getSearchedShowsByShowTitlesWithInception() {
        String[] result = getShowsByTitle("inception");
        String[] actual = new String[]{"Inception"};

        assertArrayEquals(actual, result);
    }

    @Test
    void getSearchedShowsByShowTitlesWithNullValue() {
            assertThrows(
                    NullPointerException.class,
                    () -> ShowController.getSearchedShowsByShowTitles(null),
                    "Invalid null character should throw (NullPointerException)");
    }

    @Test
    void getSearchedShowsByShowTitlesWithEmptyString() {
        String[] result = getShowsByTitle("");
        String[] actual = new String[]{"Star Wars: Episode IX - The Rise of Skywalker",
                                        "Star Wars: Episode III - Revenge of The Sith",
                                        "Star Wars: Episode V - The Empire strikes Back",
                                        "Star Wars: Episode VII - The Force Awakens",
                                        "Inception",
                                        "Avengers: Endgame"};

        assertArrayEquals(actual, result);
    }

    @Test
    void getSearchedShowsByShowTitlesWithNonExistentShow() {
        String[] result = getShowsByTitle("frozen");
        String[] actual = new String[]{};

        assertArrayEquals(actual, result);
    }

    @Test
    void getSearchedShowsByShowTitlesWithUpperCaseLetters() {
        String[] result = getShowsByTitle("SKYWALKER");
        String[] actual = new String[]{"Star Wars: Episode IX - The Rise of Skywalker"};

        assertArrayEquals(actual, result);
    }

    @Test
    void getSearchedShowsByActorsWithTom() {
        String[] result = getShowsByActor("tom");
        String[] actual = new String[]{"Inception"};

        assertArrayEquals(actual, result);
    }

    @Test
    void getSearchedShowsByActorsWithNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> ShowController.getSearchedShowsByActors(null),
                "Invalid null character should throw (NullPointerException)");
    }

    @Test
    void getSearchedShowsByActorsWithEmptyString() {
        String[] result = getShowsByActor("");
        String[] actual = new String[]{"Star Wars: Episode IX - The Rise of Skywalker",
                                        "Star Wars: Episode VII - The Force Awakens",
                                        "Star Wars: Episode III - Revenge of The Sith",
                                        "Star Wars: Episode V - The Empire strikes Back",
                                        "Inception",
                                        "Avengers: Endgame"};

        assertArrayEquals(actual, result);
    }

    @Test
    void getSearchedShowsByActorsWithNonExistentActor() {
        String[] result = getShowsByActor("william");
        String[] actual = new String[]{};

        assertArrayEquals(actual, result);
    }

    @Test
    void getSearchedShowsByActorsWithUpperCaseLetters() {
        String[] result = getShowsByActor("TOM");
        String[] actual = new String[]{"Inception"};

        assertArrayEquals(actual, result);
    }
}