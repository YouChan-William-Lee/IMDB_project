package app.controller;

import app.controller.paths.Template;
import app.dao.CastDao;
import io.javalin.http.Handler;
import java.util.Map;
import app.controller.utils.ViewUtil;
import static app.Main.*;
import static app.controller.utils.RequestUtil.*;





public class ShowController {

    public static Handler fetchAllShows = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("shows", showDao.getAllShows());
        ctx.render(Template.SHOWS_ALL, model);
    };

    public static Handler fetchOneShow = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("show", showDao.getShowByShowId(getParamShowId(ctx)));
        ctx.render(Template.SHOWS_ONE, model);
    };

    public static Handler fetchSearchedShowsPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        System.out.println(getParamSearchtext(ctx));

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
}
