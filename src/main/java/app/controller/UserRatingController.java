package app.controller;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.model.ShowEntities.UserRating;
import io.javalin.http.Handler;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static app.Main.*;
import static app.controller.utils.RequestUtil.*;


public class UserRatingController {

    //add show rating
    public static Handler handleRatingPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        if(newUserRating(getFormShowId(ctx),getSessionCurrentUser(ctx),Integer.parseInt(getParamRating(ctx)))) {
            model.put("posted", true);
        }
        else {
            model.put("posted", false);
        }
        model.put("show", showDao.getShowByShowId(getFormShowId(ctx)));
        model.put("user", userDao.getUserByUsername(getSessionCurrentUser(ctx)));
        model.put("casts", castDao.getAllCast());
        model.put("reviews",userReviewDao.getAllReviewByShowId(getFormShowId(ctx)));
        model.put("feedbacks",userFeedbackDao.getAllFeedbackByShowId(getFormShowId(ctx)));
        model.put("ratings",userRatingDao.getAllRatingByShowId(getFormShowId(ctx)));
        ctx.render(Template.SHOWS_ONE, model);
    };

    public static boolean newUserRating(String showId, String userId, int rating) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date
        String dateStr = format.format(date);
        UserRating userrating = new UserRating(Integer.parseInt(showId),userId,rating,dateStr);
        if(userrating != null) {
            userRatingDao.addUserRating(userrating);
            return true;
        }
        else {
            return false;
        }
    }
}