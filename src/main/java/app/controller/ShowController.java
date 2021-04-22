package app.controller;

import app.controller.paths.Template;
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
        model.put("show", showDao.getShowByIsbn(getParamIsbn(ctx)));
        ctx.render(Template.SHOWS_ONE, model);
    };
}
