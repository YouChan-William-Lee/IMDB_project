package app.dao;

import app.model.Cast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CastDao {

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
}
