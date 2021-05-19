package app.dao;

import app.model.ShowEntities.UserFeedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFeedbackDao extends Database {

    //Add this user review into database
    public void addUserFeedback(UserFeedback userFeedback) {
        String sql= "insert into imdb.user_feedback( show_id, user_id, feedback, date) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
//            preparedStatement.setInt(1, userReview.getReviewId());
            preparedStatement.setInt(1, userFeedback.getShowId());
            preparedStatement.setString(2, userFeedback.getUserId());
            preparedStatement.setString(3, userFeedback.getFeedback());
            preparedStatement.setString(4,userFeedback.getDate().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Iterable<UserFeedback> getAllFeedbackByShowId(String showId) {
        List<UserFeedback> userFeedbackList = new ArrayList<UserFeedback>();

        String sql;
        try {
            sql = "select * from user_feedback where show_id=?";
            setPreparedStatement(sql);
            preparedStatement.setString(1, showId);
            ResultSet rs = preparedStatement.executeQuery();
            while (true) {
                if (rs.next()) {
                    userFeedbackList.add(new UserFeedback(rs.getInt("show_id"),rs.getString("user_id"),rs.getString("feedback"),rs.getString("date")));
                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userFeedbackList;
    }

    public Iterable<UserFeedback> getAllFeedback() {
        List<UserFeedback> userFeedbackList = new ArrayList<UserFeedback>();
        String sql;
        try {
            sql = "select * from user_feedback";
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (true) {
                if (rs.next()) {
                    userFeedbackList.add(new UserFeedback(rs.getInt("show_id"),rs.getString("user_id"),rs.getString("feedback"),rs.getString("date")));
                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFeedbackList;
    }

}
