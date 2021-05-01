package app.controller;

import app.controller.paths.Template;
import app.model.ShowEntities.Cast;
import app.model.ShowEntities.ProductionCo;
import io.javalin.http.Handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import app.controller.utils.ViewUtil;
import static app.Main.*;
import static app.controller.utils.RequestUtil.*;
import app.model.ShowEntities.Show;


public class ShowController {

    public static Handler fetchAllShows = ctx -> { //Get all shows
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("shows", showDao.getAllShows());
        model.put("user", userDao.getUserByUsername(getSessionCurrentUser(ctx)));
        ctx.render(Template.SHOWS_ALL, model);
    };

    public static Handler fetchOneShow = ctx -> { //Get a show via showID,Username,casts
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("show", showDao.getShowByShowId(getParamShowId(ctx)));
        model.put("user", userDao.getUserByUsername(getSessionCurrentUser(ctx)));
        model.put("casts", castDao.getAllCast());
        ctx.render(Template.SHOWS_ONE, model);
    };
        // searching shows via titles or actors
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

    public static Handler fetchAddNewPage = ctx -> { // adding new show page
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        model.put("user", userDao.getUserByUsername(getSessionCurrentUser(ctx)));
        model.put("showid", showDao.getNumberOfShows() + 1);

        ctx.render(Template.ADDMINADDSHOW, model);
    };

    //adding new shows cases: 1. show already exist 2. show is new, then system asks for more
    // info. of the new show
    public static Handler fetchAddNewPagePost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        if (!ShowController.duplicationCheck(getQueryShowtitle(ctx))) {
            model.put("duplicationCheckFailed", true);
            ctx.render(Template.ADDMINADDSHOW, model);
        } else {


            ProductionCo productionCo = productionCoDao.getProductionCo(getQueryShowPCO(ctx));
            if (productionCo == null) {
                productionCo = new ProductionCo(productionCoDao.getNumberOfProductionCo() + 1, getQueryShowPCO(ctx));
                productionCoDao.addProductionCo(productionCo);
            }

            int showId = showDao.getNumberOfShows() + 1;
            Show newShow = new Show(showId, getQueryShowtitle(ctx), getQueryShowgenre(ctx), getQueryShowlength(ctx), getQueryShowmovie(ctx), getQueryShowseries(ctx), String.valueOf(productionCo.getId()), getQueryShowyear(ctx), getQueryShowimageaddress(ctx), null);
            showDao.addShow(newShow);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

            Cast cast1 = castDao.getCast(getQueryShowcreditsroll1actorname(ctx));
            if (cast1 == null) {
                long date1 = ((Date)formatter.parse(getQueryShowcreditsroll1birthday(ctx))).getTime();
                cast1 = new Cast(castDao.getNumberOfCasts() + 1, getQueryShowcreditsroll1actorname(ctx), "Actor", new java.sql.Date(date1), getQueryShowcreditsroll1bio(ctx));
                castDao.addCast(cast1);
            }

            Cast cast2 = castDao.getCast(getQueryShowcreditsroll2actorname(ctx));
            if (cast2 == null) {
                long date2 = ((Date)formatter.parse(getQueryShowcreditsroll2birthday(ctx))).getTime();
                cast2 = new Cast(castDao.getNumberOfCasts() + 1, getQueryShowcreditsroll2actorname(ctx), "Actor", new java.sql.Date(date2), getQueryShowcreditsroll2bio(ctx));
                castDao.addCast(cast2);
            }

            castDao.addCastToShow(cast1, newShow, getQueryShowcreditsroll1charactername(ctx));
            castDao.addCastToShow(cast2, newShow, getQueryShowcreditsroll2charactername(ctx));


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
