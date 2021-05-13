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

    //add show userreview
    public static Handler handleEvaluatePost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        String user = getSessionCurrentUser(ctx);
        if(StringUtils.isNotBlank(user)){
            UserReviewController.newUserReview(getFormShowId(ctx),user,getParamRating(ctx),getParamReview(ctx));
        }
        model.put("show", showDao.getShowByShowId(getFormShowId(ctx)));
        model.put("user", userDao.getUserByUsername(getSessionCurrentUser(ctx)));
        model.put("casts", castDao.getAllCast());
        model.put("reviews",userReviewDao.getAllReviewByShowId(getFormShowId(ctx)));
        ctx.render(Template.SHOWS_ONE, model);
    };

    public static void newUserReview(String showId, String userId,String rating,String review) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date
        String dateStr = format.format(date);
        userReviewDao.addUserReview(new UserReview(Integer.parseInt(showId),
                userId,Integer.parseInt(rating),review,dateStr));

    }
}
