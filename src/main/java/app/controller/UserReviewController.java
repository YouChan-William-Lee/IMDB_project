package app.controller;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.model.ShowEntities.UserReview;
import io.javalin.http.Handler;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static app.Main.*;
import static app.controller.utils.RequestUtil.*;


public class UserReviewController {

    //add show user review
    public static Handler handleReviewPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        if(newUserReview(getFormShowId(ctx),getSessionCurrentUser(ctx),getParamReview(ctx))) {
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

    public static boolean newUserReview(String showId, String username, String review) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date
        String dateStr = format.format(date);
        UserReview userreview = new UserReview(Integer.parseInt(showId),username,review,dateStr);
        if(userreview != null) {
            userReviewDao.addUserReview(userreview);
            return true;
        }
        else {
            return false;
        }
    }
}
