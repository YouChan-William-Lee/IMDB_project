package app.controller;

import app.controller.paths.Template;
import io.javalin.http.Handler;
import java.util.Map;
import app.controller.utils.ViewUtil;
import static app.controller.utils.RequestUtil.*;

public class SigninController {
    // logout, then return to signin page again
    public static Handler serveSigninPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("loggedOut", removeSessionAttrLoggedOut(ctx));
        model.put("signinRedirect", removeSessionAttrLoginRedirect(ctx));
        ctx.render(Template.SIGNIN, model);
    };


    //Sign in Cases: 1. username already exists, then return to signin page again
    //               2. username is new, signin succeed, then renders the page
    public static Handler handleSigninPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        String thisuser = getQueryUsername(ctx);
        //Check whether this username already exists
        if (!UserController.duplicationCheck(getQueryUsername(ctx))) {
            model.put("duplicationCheckFailed", true);
            ctx.render(Template.SIGNIN, model);
        } else {
            UserController.newUser(getQueryUsername(ctx), getQueryPassword(ctx), getQueryFirstname(ctx), getQueryLastname(ctx), getQueryEmail(ctx), getQueryGender(ctx), getQueryTypeOfUser(ctx), getQueryCountry(ctx));
            // Check if this user's request is pending
            if(LoginController.approvedCheck(getQueryUsername(ctx))) {
                //Add this user into database
                ctx.sessionAttribute("currentUser", getQueryUsername(ctx));
                model.put("duplicationCheckSucceeded", true);
                model.put("currentUser", getQueryUsername(ctx));
                if (getQueryLoginRedirect(ctx) != null) {
                    ctx.redirect(getQueryLoginRedirect(ctx));
                }
                ctx.render(Template.SIGNIN, model);
            }
            else {
                model.put("loggedOut", removeSessionAttrLoggedOut(ctx));
                model.put("signinRedirect", removeSessionAttrLoginRedirect(ctx));
                model.put("approvedFailed", true);
                model.put("pendingUser", thisuser);
                ctx.render(Template.SIGNIN, model);
            }
        }
    };}
