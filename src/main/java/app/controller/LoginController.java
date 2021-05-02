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

    // Login Cases: login failed, system returns to login page/ login succeed
    public static Handler handleLoginPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        // When the information from user does not match with the user in database, it shows "fail"
        if (!UserController.authenticate(getQueryUsername(ctx), getQueryPassword(ctx))) {
            model.put("authenticationFailed", true);
            ctx.render(Template.LOGIN, model);
        // When the information from user matches with the user in database, it shows "success"
        }
        else {
            // make this user to current session user
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
        // When user presses log out button
        ctx.sessionAttribute("currentUser", null);
        ctx.sessionAttribute("loggedOut", "true");
        ctx.redirect(Web.LOGIN);
    };
}
