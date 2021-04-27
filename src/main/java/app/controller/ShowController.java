package app.controller;

import app.controller.paths.Template;
import app.dao.CastDao;
import app.dao.ShowDao;
import app.model.Cast;
import io.javalin.http.Handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import app.controller.utils.ViewUtil;
import static app.Main.*;
import static app.controller.utils.RequestUtil.*;
import app.model.Show;
import app.dao.ShowDao;




public class ShowController {

    public static Handler fetchAllShows = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("shows", showDao.getAllShows());
        model.put("user", userDao.getUserByUsername(getSessionCurrentUser(ctx)));
        ctx.render(Template.SHOWS_ALL, model);
    };

    public static Handler fetchOneShow = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("show", showDao.getShowByShowId(getParamShowId(ctx)));
        model.put("user", userDao.getUserByUsername(getSessionCurrentUser(ctx)));
        ctx.render(Template.SHOWS_ONE, model);
    };

    public static Handler fetchSearchedShowsPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        if(getParamSearchoption(ctx).equals("Titles")) {
            model.put("shows", showDao.getSearchedShowsByShowTitles(getParamSearchtext(ctx)));
            model.put("searchedText", getParamSearchtext(ctx));
        }
        else if(getParamSearchoption(ctx).equals("Actors")) {
            model.put("shows", castDao.getSearchedShowsByActors(getParamSearchtext(ctx)));
            model.put("searchedText", getParamSearchtext(ctx));
        }

        ctx.render(Template.SEARCH, model);
    };

    public static Handler fetchAddNewPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        model.put("user", userDao.getUserByUsername(getSessionCurrentUser(ctx)));
        model.put("showid", showDao.getNumberOfShows() + 1);

        ctx.render(Template.ADDMINADDSHOW, model);
    };

    public static Handler fetchAddNewPagePost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        if (!ShowController.duplicationCheck(getQueryShowtitle(ctx))) {
            model.put("duplicationCheckFailed", true);
            ctx.render(Template.ADDMINADDSHOW, model);
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            Date date1 = (Date)formatter.parse(getQueryShowcreditsroll1birthday(ctx));
            Date date2 = (Date)formatter.parse(getQueryShowcreditsroll2birthday(ctx));
            Cast person1 = CastDao.getPerson(getQueryShowcreditsroll1actorname(ctx), date1, getQueryShowcreditsroll1bio(ctx));
            Cast person2 = CastDao.getPerson(getQueryShowcreditsroll2actorname(ctx), date2, getQueryShowcreditsroll2bio(ctx));
            Map<String, String> creditsRoll = new HashMap<String, String>();
            creditsRoll.put(getQueryShowcreditsroll1charactername(ctx), person1.getName());
            creditsRoll.put(getQueryShowcreditsroll2charactername(ctx), person2.getName());
            Show newShow = new Show(showDao.getNumberOfShows() + 1, getQueryShowtitle(ctx), getQueryShowgenre(ctx), getQueryShowlength(ctx), getQueryShowmovie(ctx), getQueryShowseries(ctx), getQueryShowPCO(ctx), getQueryShowyear(ctx), getQueryShowimageaddress(ctx), creditsRoll);
            ShowDao.addShowToDatabase(newShow);
            model.put("duplicationCheckSucceeded", true);
            ctx.render(Template.ADDMINADDSHOW, model);
        }
    };

    public static boolean duplicationCheck(String showTitle) {
        if(showTitle == null) {
            return false;
        }
        Show show = showDao.getShowByShowTitle(showTitle);
        return show == null;
    }
}
