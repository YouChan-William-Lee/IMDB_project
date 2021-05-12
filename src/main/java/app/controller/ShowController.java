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
    //Get all shows and current user name
    public static Handler fetchAllShows = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("deleteSucceded", false);
        model.put("shows", showDao.getApprovedShows());
        model.put("user", userDao.getUserByUsername(getSessionCurrentUser(ctx)));
        ctx.render(Template.SHOWS_ALL, model);
    };

    //Get a show by showID, current user name, and all the casts
    public static Handler fetchOneShow = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("show", showDao.getShowByShowId(getParamShowId(ctx)));
        model.put("user", userDao.getUserByUsername(getSessionCurrentUser(ctx)));
        model.put("casts", castDao.getAllCast());
        ctx.render(Template.SHOWS_ONE, model);
    };

    //Search shows by titles or actors
    public static Handler fetchSearchedShowsPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        // If user chooses "Titles", then searches the shows contain this title
        if(getParamSearchoption(ctx).equals("Titles")) {
            model.put("shows", showDao.getSearchedShowsByShowTitles(getParamSearchtext(ctx)));
            model.put("searchedText", getParamSearchtext(ctx));
        }
        // If user chooses "Actors", then searches the shows cast this actor
        else if(getParamSearchoption(ctx).equals("Actors")) {
            model.put("shows", castDao.getSearchedShowsByActors(getParamSearchtext(ctx)));
            model.put("searchedText", getParamSearchtext(ctx));
        }

        ctx.render(Template.SEARCH, model);
    };

    //Admin adds a new show
    public static Handler fetchAddNewPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        model.put("user", userDao.getUserByUsername(getSessionCurrentUser(ctx)));
        model.put("showid", showDao.getNumberOfShows() + 1);

        ctx.render(Template.useraddshow, model);
    };

    //Admin submits the new show's information
    public static Handler fetchAddNewPagePost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("showid", showDao.getNumberOfShows() + 1);
        //Check whether same show title exists or not
        if (!ShowController.duplicationCheck(getQueryShowtitle(ctx))) {
            model.put("duplicationCheckFailed", true);
            ctx.render(Template.useraddshow, model);
        }
        else {
            //Check same production company already exists in database
            ProductionCo productionCo = productionCoDao.getProductionCo(getQueryShowPCO(ctx));
            if (productionCo == null) {
                productionCo = new ProductionCo(productionCoDao.getNumberOfProductionCo() + 1, getQueryShowPCO(ctx));
                productionCoDao.addProductionCo(productionCo);
            }
            //Increase show ID automatically
            int showId = showDao.getNumberOfShows() + 1;
            Show newShow;
            if(userDao.getUserByUsername(getSessionCurrentUser(ctx)).getTypeOfUser().equals("admin")) {
                newShow = new Show(showId, getQueryShowtitle(ctx), getQueryShowgenre(ctx), getQueryShowlength(ctx), getQueryShowmovie(ctx), getQueryShowseries(ctx), String.valueOf(productionCo.getId()), getQueryShowyear(ctx), true, getQueryShowimageaddress(ctx), null);
            }
            else {
                newShow = new Show(showId, getQueryShowtitle(ctx), getQueryShowgenre(ctx), getQueryShowlength(ctx), getQueryShowmovie(ctx), getQueryShowseries(ctx), String.valueOf(productionCo.getId()), getQueryShowyear(ctx), false, getQueryShowimageaddress(ctx), null);
                model.put("approvedFailed", true);
            }

            //Add this new show into database
            showDao.addShow(newShow);
            //Format date form
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            //Check same cast already exists in database
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
            //Add these casts to new show
            castDao.addCastToShow(cast1, newShow, getQueryShowcreditsroll1charactername(ctx));
            castDao.addCastToShow(cast2, newShow, getQueryShowcreditsroll2charactername(ctx));

            model.put("duplicationCheckSucceeded", true);
            ctx.render(Template.useraddshow, model);
        }
    };

    public static Handler fetchDeleteShowPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("deletedShowTitle", showDao.getShowByShowId(getParamShowId(ctx)).getShowTitle());
        showDao.deleteShow(showDao.getShowByShowId(getParamShowId(ctx)));

        model.put("deleteSucceded", true);
        model.put("shows", showDao.getAllShows());
        model.put("user", userDao.getUserByUsername(getSessionCurrentUser(ctx)));

        ctx.render(Template.SHOWS_ALL, model);
    };

    //Check whether show title already exists
    public static boolean duplicationCheck(String showTitle) {
        if(showTitle == null) {
            return false;
        }
        //Find the show which has this showTitle
        //If there is no show has this showTitle, then return value will be null
        Show show = showDao.getShowByShowTitle(showTitle);
        //This means there is no show has this show title, and finally return true
        return show == null;
    }
}
