package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import app.model.ShowEntities.Show;

public class ShowDao extends Database {


    // Adding shows process
    public void addShow(Show show) {
        String sql= "insert into imdb.show(showid, show_title, genre, length, movie, series, proco_id, year, imageAddress) values(?,?,?,?,?,?,?,?,?)" ;
        try {
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
            preparedStatement.setInt(1, show.getShowID());
            preparedStatement.setString(2, show.getShowTitle());
            preparedStatement.setString(3, show.getGenre());
            preparedStatement.setString(4, show.getLength());
            preparedStatement.setString(5, show.getMovie());
            preparedStatement.setString(6, show.getSeries());
            preparedStatement.setString(7, show.getPCO());
            preparedStatement.setString(8, show.getYear());
            preparedStatement.setString(9, show.getCover());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get the list of the shows
    public Iterable<Show> getAllShows() {

        List<Show> results = new ArrayList<Show>();
        String sql;
        try{
            sql = "select * from imdb.show";
            ResultSet showRS = setPreparedStatement(sql).executeQuery();;
//

            while (true) {
                if (showRS.next()) {

                    sql = "select * from production_company where proco_id=?";
                    setPreparedStatement(sql).setString(1, showRS.getString("proco_id"));
                    ResultSet productionRS = preparedStatement.executeQuery();
                    String production = "";
                    if (productionRS.next()) {
                        production = productionRS.getString("proco_name");
                    }

                    sql = "select * from credits_roll where show_id=?";
                    setPreparedStatement(sql).setString(1, showRS.getString("showid"));
                    ResultSet creditRollRS = preparedStatement.executeQuery();

                    Map<String, String> cast = new HashMap<String, String>();
                    while (true) {
                        if (creditRollRS.next()) {

                            sql = "select * from person where person_id=?";
                            setPreparedStatement(sql).setString(1, creditRollRS.getString("person_id"));
                            ResultSet personRS = preparedStatement.executeQuery();

                            if (personRS.next()) {
                                cast.put(creditRollRS.getString("character_name"), personRS.getString("fullname"));
                            }
                        } else {
                            break;
                        }
                    }


                    addShowToList(results, new Show(Integer.parseInt(showRS.getString("showid")), showRS.getString("show_title"), showRS.getString("genre"), showRS.getString("length"), showRS.getString("movie"), showRS.getString("series"), production, showRS.getString("year"), showRS.getString("imageAddress"), cast));

                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public int getNumberOfShows() {
        List<Show> shows = (List<Show>) getAllShows();
        return shows.size();
    }

    // Obtaining the ID of a show
    public Show getShowByShowId(String showId) {
        List<Show> shows = (List<Show>) getAllShows();
        for(int i = 0; i < shows.size(); i++) {
            if(shows.get(i).getShowID() == Integer.parseInt(showId)) {
                return shows.get(i);
            }
        }
        return null;
    }

    // Getting the title of a specific show
    public Show getShowByShowTitle(String showTitle) {
        List<Show> shows = (List<Show>) getAllShows();
        for(int i = 0; i < shows.size(); i++) {
            if(shows.get(i).getShowTitle().equals(showTitle)) {
                return shows.get(i);
            }
        }
        return null;
    }

    // Showing a random show
    public Show getRandomShow() {
        List<Show> shows = (List<Show>) getAllShows();
        return shows.get(new Random().nextInt(shows.size()));
    }

    public Iterable<Show> getSearchedShowsByShowTitles(String searching) {
        List<Show> searchedShows = new ArrayList<Show>();
        List<Show> shows = (List<Show>) getAllShows();
        for(Show s : shows) {
            String title = s.getShowTitle();
            if(title.toUpperCase().contains(searching.toUpperCase())) {
                addShowToList(searchedShows, s);
            }
        }
        return searchedShows;
    }


    //Adding show to the show list
    public void addShowToList(List<Show> list, Show s) {
        list.add(s);
    }
}
