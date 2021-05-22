package app.controller;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.model.ShowEntities.UserFeedback;
import io.javalin.http.Handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static app.Main.*;
import static app.controller.utils.RequestUtil.*;


public class UserFeedbackController {

    //add show feedback
    public static Handler handleFeedbackPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        if(newUserFeedback(getFormShowId(ctx),getSessionCurrentUser(ctx),getParamFeedback(ctx))) {
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

    public static boolean newUserFeedback(String showId, String username, String feedback) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date
        String dateStr = format.format(date);
        UserFeedback userfeedback = new UserFeedback(Integer.parseInt(showId),username,feedback,dateStr);
        if(userfeedback != null) {
            userFeedbackDao.addUserFeedback(userfeedback);
            return true;
        }
        else {
            return false;
        }
    }
}