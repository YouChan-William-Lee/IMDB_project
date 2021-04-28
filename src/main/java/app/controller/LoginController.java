package app.controller;

import app.controller.paths.Template;
import app.controller.paths.Web;
import io.javalin.http.Handler;
import java.util.Map;
import app.controller.utils.ViewUtil;
import static app.controller.utils.RequestUtil.*;




public class LoginController {

    // logout and then return to login page
    public static Handler serveLoginPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("loggedOut", removeSessionAttrLoggedOut(ctx));
        model.put("loginRedirect", removeSessionAttrLoginRedirect(ctx));
        ctx.render(Template.LOGIN, model);
    };

    public static Handler handleLoginPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        if (!UserController.authenticate(getQueryUsername(ctx), getQueryPassword(ctx))) {
            model.put("authenticationFailed", true);
            ctx.render(Template.LOGIN, model);
        } else {
            ctx.sessionAttribute("currentUser", getQueryUsername(ctx));
            model.put("authenticationSucceeded", true);
            model.put("currentUser", getQueryUsername(ctx));
            if (getQueryLoginRedirect(ctx) != null) {
                ctx.redirect(getQueryLoginRedirect(ctx));
            }
            ctx.render(Template.LOGIN, model);
        }
    };

    public static Handler handleLogoutPost = ctx -> {
        ctx.sessionAttribute("currentUser", null);
        ctx.sessionAttribute("loggedOut", "true");
        ctx.redirect(Web.LOGIN);
    };


    // The origin of the request (request.pathInfo()) is saved in the session so
    // the user can be redirected back after login
//    public static Handler ensureLoginBeforeViewingShows = ctx -> {
//        if (!ctx.path().startsWith("/shows")) {
//            return;
//        }
//        if (ctx.sessionAttribute("currentUser") == null) {
//            ctx.sessionAttribute("loginRedirect", ctx.path());
//            ctx.redirect(Web.LOGIN);
//        }
//    };

}
