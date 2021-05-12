package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import app.model.ShowEntities.Show;
import app.model.Users.User;

public class ShowDao extends Database {

    //Add this show into database
    public void addShow(Show show) {
        String sql= "insert into imdb.show(showid, show_title, genre, length, movie, series, proco_id, year, approved, imageAddress) values(?,?,?,?,?,?,?,?,?,?)";
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
            preparedStatement.setBoolean(9, show.getApproved());
            preparedStatement.setString(10, show.getCover());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateShow(Show show) {
        // Add this user into database.
        String sql= "update imdb.show SET showid=?, show_title=?, genre=?, length=?, movie=?, series=?, proco_id=?, year=?, approved=?, imageAddress=? where showid=?";
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
            preparedStatement.setBoolean(9, show.getApproved());
            preparedStatement.setString(10, show.getCover());
            preparedStatement.setInt(11, show.getShowID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteShow(Show show) {
        //Delete credits_roll in this show
        String sql= "delete from imdb.credits_roll where show_id = ?" ;
        try {
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
            preparedStatement.setInt(1, show.getShowID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Delete show
        sql= "delete from imdb.show where showid = ?" ;
        try {
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
            preparedStatement.setInt(1, show.getShowID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Get all the shows
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
                    addShowToList(results, new Show(Integer.parseInt(showRS.getString("showid")), showRS.getString("show_title"), showRS.getString("genre"), showRS.getString("length"), showRS.getString("movie"), showRS.getString("series"), production, showRS.getString("year"), showRS.getBoolean("approved"), showRS.getString("imageAddress"), cast));

                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    //Get approved shows
    public Iterable<Show> getApprovedShows() {

        List<Show> results = new ArrayList<Show>();
        String sql;
        try{
            sql = "select * from imdb.show where approved = 1";
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
                    addShowToList(results, new Show(Integer.parseInt(showRS.getString("showid")), showRS.getString("show_title"), showRS.getString("genre"), showRS.getString("length"), showRS.getString("movie"), showRS.getString("series"), production, showRS.getString("year"), showRS.getBoolean("approved"), showRS.getString("imageAddress"), cast));

                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    //Get the number of shows
    public int getNumberOfShows() {
        List<Show> shows = (List<Show>) getAllShows();
        return shows.size();
    }

    //Get the show by show id
    public Show getShowByShowId(String showId) {
        List<Show> shows = (List<Show>) getAllShows();
        for(int i = 0; i < shows.size(); i++) {
            if(shows.get(i).getShowID() == Integer.parseInt(showId)) {
                return shows.get(i);
            }
        }
        return null;
    }

    //Get the show by show title
    public Show getShowByShowTitle(String showTitle) {
        List<Show> shows = (List<Show>) getAllShows();
        for(int i = 0; i < shows.size(); i++) {
            if(shows.get(i).getShowTitle().equals(showTitle)) {
                return shows.get(i);
            }
        }
        return null;
    }

    //Get a random show
    public Show getRandomShow() {
        List<Show> shows = (List<Show>) getApprovedShows();
        return shows.get(new Random().nextInt(shows.size()));
    }

    //Get the shows by show title by user
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


    //Add this show to the show list
    public void addShowToList(List<Show> list, Show s) {
        list.add(s);
    }
}
