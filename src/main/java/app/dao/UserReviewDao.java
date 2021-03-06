package app.dao;

import app.model.ShowEntities.UserReview;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserReviewDao extends Database {

    //Add this user review into database
    public void addUserReview(UserReview userReview) {
        String sql= "insert into imdb.user_review( show_id, username, review, date) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
//            preparedStatement.setInt(1, userReview.getReviewId());
            preparedStatement.setInt(1, userReview.getShowId());
            preparedStatement.setString(2, userReview.getUsername());
            preparedStatement.setString(3, userReview.getReview());
            preparedStatement.setString(4,userReview.getDate().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Iterable<UserReview> getAllReviewByShowId(String showId) {
        List<UserReview> userReviewList = new ArrayList<UserReview>();

        String sql;
        try {
            sql = "select * from user_review where show_id=?";
            setPreparedStatement(sql);
            preparedStatement.setString(1, showId);
            ResultSet rs = preparedStatement.executeQuery();
            while (true) {
                if (rs.next()) {
                    userReviewList.add(new UserReview(rs.getInt("show_id"),rs.getString("username"),rs.getString("review"),rs.getString("date")));
                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userReviewList;
    }

    public Iterable<UserReview> getAllReview() {
        List<UserReview> userReviewList = new ArrayList<UserReview>();
        String sql;
        try {
            sql = "select * from user_review";
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (true) {
                if (rs.next()) {
                    userReviewList.add(new UserReview(rs.getInt("show_id"),rs.getString("username"),rs.getString("review"),rs.getString("date")));
                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userReviewList;
    }

}
