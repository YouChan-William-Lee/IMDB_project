package app.dao;

import app.model.Cast;
import app.model.Show;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CastDao extends Database {

    private static List<Cast> allCast = new ArrayList<Cast>();;

    public static Cast getPerson(String name, Date birthdate, String bio) {

        for (Cast person : allCast) {
            if (person.getName().equals(name)) {
                return person;
            }
        }

        Cast newPerson = new Cast(name, birthdate, bio);
        allCast.add(newPerson);
        return newPerson;
    }

    public Iterable<Show> getSearchedShowsByActors(String searching) {
        List<Show> searchedShows = new ArrayList<Show>();
        for(int i = 0; i < allCast.size(); i++) {
            if(allCast.get(i).getName().toUpperCase().contains(searching.toUpperCase())) {
                searchedShows.addAll(allCast.get(i).getAllShows());
            };
        }

        return searchedShows;
    }
}
