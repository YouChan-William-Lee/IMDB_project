package app.controller;

import app.controller.paths.Template;
import app.dao.CastDao;
import app.dao.ProductionCoDao;
import app.dao.ShowDao;
import app.model.Cast;
import app.model.ProductionCo;
import io.javalin.http.Handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import app.controller.utils.ViewUtil;
import static app.Main.*;
import static app.controller.utils.RequestUtil.*;
import app.model.Show;


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

    public static Handler fetchAddNewPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        model.put("user", userDao.getUserByUsername(getSessionCurrentUser(ctx)));
        model.put("showid", showDao.getNumberOfShows() + 1);

        ctx.render(Template.ADDMINADDSHOW, model);
    };

    //adding new shows
    public static Handler fetchAddNewPagePost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        if (!ShowController.duplicationCheck(getQueryShowtitle(ctx))) {
            model.put("duplicationCheckFailed", true);
            ctx.render(Template.ADDMINADDSHOW, model);
        } else {


            ProductionCo productionCo = ProductionCoDao.getProductionCo(getQueryShowPCO(ctx));
            if (productionCo == null) {
                productionCo = new ProductionCo(ProductionCoDao.getNumberOfProductionCo() + 1, getQueryShowPCO(ctx));
                ProductionCoDao.addProductionCo(productionCo);
            }

            int showId = showDao.getNumberOfShows() + 1;
            Show newShow = new Show(showId, getQueryShowtitle(ctx), getQueryShowgenre(ctx), getQueryShowlength(ctx), getQueryShowmovie(ctx), getQueryShowseries(ctx), String.valueOf(productionCo.getId()), getQueryShowyear(ctx), getQueryShowimageaddress(ctx), null);
            ShowDao.addShow(newShow);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

            Cast cast1 = CastDao.getCast(getQueryShowcreditsroll1actorname(ctx));
            if (cast1 == null) {
                long date1 = ((Date)formatter.parse(getQueryShowcreditsroll1birthday(ctx))).getTime();
                cast1 = new Cast(CastDao.getNumberOfCasts() + 1, getQueryShowcreditsroll1actorname(ctx), "Actor", new java.sql.Date(date1), getQueryShowcreditsroll1bio(ctx));
                CastDao.addCast(cast1);
            }

            Cast cast2 = CastDao.getCast(getQueryShowcreditsroll2actorname(ctx));
            if (cast2 == null) {
                long date2 = ((Date)formatter.parse(getQueryShowcreditsroll2birthday(ctx))).getTime();
                cast2 = new Cast(CastDao.getNumberOfCasts() + 1, getQueryShowcreditsroll2actorname(ctx), "Actor", new java.sql.Date(date2), getQueryShowcreditsroll2bio(ctx));
                CastDao.addCast(cast2);
            }

            CastDao.addCastToShow(cast1, newShow, getQueryShowcreditsroll1charactername(ctx));
            CastDao.addCastToShow(cast2, newShow, getQueryShowcreditsroll2charactername(ctx));



//            Cast person1 = CastDao.getPerson(getQueryShowcreditsroll1actorname(ctx), date1, getQueryShowcreditsroll1bio(ctx));
//            Cast person2 = CastDao.getPerson(getQueryShowcreditsroll2actorname(ctx), date2, getQueryShowcreditsroll2bio(ctx));
//            Map<String, String> creditsRoll = new HashMap<String, String>();
//            creditsRoll.put(getQueryShowcreditsroll1charactername(ctx), person1.getName());
//            creditsRoll.put(getQueryShowcreditsroll2charactername(ctx), person2.getName());


//            creditsRoll.put("Jack Dawson", "Leonardo DiCaprio");
//            creditsRoll.put("Rose Dewitt Bukater", "Kate Winslet");
//            Show newShow = new Show(showDao.getNumberOfShows() + 1, "Titanic", "Drama", "3.14", "1", "0", "3", "1997", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhYjUIu2o5v5u3rfJpCq5Cz0Q9WK--XdYxai_N2d0ImohPiIOp", creditsRoll);

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
