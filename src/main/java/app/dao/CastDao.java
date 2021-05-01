package app.dao;

import app.model.ShowEntities.Cast;
import app.model.ShowEntities.Show;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static app.Main.showDao;

public class CastDao extends Database {

    // Obtain shows via casts searching
    public Iterable<Show> getSearchedShowsByActors(String searching) {

        List<Show> searchedShows = new ArrayList<Show>();
        List<Cast> casts = (List<Cast>) getAllCast();
        for(Cast c : casts) {
            if (c.getName().toUpperCase().contains(searching.toUpperCase())) {

                String sql = "select show_id from credits_roll where person_id=?";
                try {
                    setPreparedStatement(sql).setInt(1, c.getID());
                    ResultSet creditRollRS = preparedStatement.executeQuery();

                    while (true) {
                        if (creditRollRS.next()) {
                            boolean duplication = false;
                            for(int i = 0; i < searchedShows.size(); i++) {
                                if(searchedShows.get(i).getShowTitle().equals(showDao.getShowByShowId(creditRollRS.getString("show_id")).getShowTitle())) {
                                    duplication = true;
                                    break;
                                }
                            }
                            if(!duplication) {
                                searchedShows.add(showDao.getShowByShowId(creditRollRS.getString("show_id")));
                            }
                        } else {
                            break;
                        }
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return searchedShows;
    }

    // Get the cast list of a show
    public Iterable<Cast> getAllCast() {
        List<Cast> allCast = new ArrayList<Cast>();

        String sql;
        try {
            sql = "select * from person";
            setPreparedStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (true) {
                if (rs.next()) {
                    allCast.add(new Cast(rs.getInt("person_id"), rs.getString("fullname"), rs.getString("role"), rs.getDate("birthdate"), rs.getString("bio")));

                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allCast;
    }

    // Get a cast of a show
    public Cast getCast(String name) {
        List<Cast> casts = (List<Cast>) getAllCast();

        for (Cast c : casts) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    // Adding casts process
    public void addCast(Cast cast) {
        String sql= "insert into person(person_id, fullname, role, birthdate, bio) values(?,?,?,?,?)" ;
        try {
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
            preparedStatement.setInt(1, cast.getID());
            preparedStatement.setString(2, cast.getName());
            preparedStatement.setString(3, cast.getRole());
            preparedStatement.setDate(4, cast.getBirthdate());
            preparedStatement.setString(5, cast.getBio());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfCasts() {
        return ((List<Cast>) getAllCast()).size();
    }

    public void addCastToShow(Cast cast, Show show, String characterName) {
        String sql= "insert into credits_roll(person_id, role, show_id, start_year, end_year, character_name) values(?,?,?,?,?,?)" ;
        try {
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
            preparedStatement.setInt(1, cast.getID());
            preparedStatement.setString(2, cast.getRole());
            preparedStatement.setString(3, String.valueOf(show.getShowID()));
            preparedStatement.setString(4, show.getYear());
            preparedStatement.setString(5, "0");
            preparedStatement.setString(6, characterName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
