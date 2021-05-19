package app.dao;

import app.model.ShowEntities.UserRating;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRatingDao extends Database {

    //Add this user review into database
    public void addUserRating(UserRating userRating) {
        String sql= "insert into imdb.user_rating( show_id, user_id, rating, date) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
//            preparedStatement.setInt(1, userReview.getReviewId());
            preparedStatement.setInt(1, userRating.getShowId());
            preparedStatement.setString(2, userRating.getUserId());
            preparedStatement.setInt(3, userRating.getRating());
            preparedStatement.setString(4,userRating.getDate().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Iterable<UserRating> getAllRatingByShowId(String showId) {
        List<UserRating> userRatingList = new ArrayList<UserRating>();

        String sql;
        try {
            sql = "select * from user_rating where show_id=?";
            setPreparedStatement(sql);
            preparedStatement.setString(1, showId);
            ResultSet rs = preparedStatement.executeQuery();
            while (true) {
                if (rs.next()) {
                    userRatingList.add(new UserRating(rs.getInt("show_id"),rs.getString("user_id"),rs.getInt("rating"),rs.getString("date")));
                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userRatingList;
    }

    public Iterable<UserRating> getAllRating() {
        List<UserRating> userRatingList = new ArrayList<UserRating>();
        String sql;
        try {
            sql = "select * from user_rating";
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (true) {
                if (rs.next()) {
                    userRatingList.add(new UserRating(rs.getInt("show_id"),rs.getString("user_id"),rs.getInt("rating"),rs.getString("date")));
                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRatingList;
    }

}
