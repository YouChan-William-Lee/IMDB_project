package app.controller;

import app.controller.paths.Template;
import io.javalin.http.Handler;
import java.util.Map;
import app.controller.utils.ViewUtil;
import static app.Main.*;

public class IndexController {
    // Serve index page which is index.vm
    public static Handler serveIndexPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        // Pass all the users
        model.put("users", userDao.getAllUsers());
        // Pass one random show
        model.put("show", showDao.getRandomShow());
        ctx.render(Template.INDEX, model);
    };
}
