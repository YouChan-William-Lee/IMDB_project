package app.controller.utils;


import io.javalin.http.Context;

public class RequestUtil {

    public static String getQueryLocale(Context ctx) { return ctx.queryParam("locale"); }

    public static String getParamShowId(Context ctx) { return ctx.pathParam("showId"); }

    public static String getParamSearchoption(Context ctx) { return ctx.formParam("searchOption"); }

    public static String getParamSearchtext(Context ctx) { return ctx.formParam("searchText"); }

    public static String getParamUsername(Context ctx) { return ctx.pathParam("username"); }

    public static String getQueryUsername(Context ctx) { return ctx.formParam("username"); }

    public static String getQueryPassword(Context ctx) {
        return ctx.formParam("password");
    }

    public static String getQueryFirstname(Context ctx) {
        return ctx.formParam("firstname");
    }

    public static String getQueryLastname(Context ctx) {
        return ctx.formParam("lastname");
    }

    public static String getQueryEmail(Context ctx) {
        return ctx.formParam("email");
    }

    public static String getQueryGender(Context ctx) {
        return ctx.formParam("gender");
    }

    public static String getQueryTypeOfUser(Context ctx){
        return  ctx.formParam("typeofuser");
    }

    public static String getQueryCountry(Context ctx) {
        return ctx.formParam("country");
    }

    public static String getQueryLoginRedirect(Context ctx) {
        return ctx.queryParam("loginRedirect");
    }

    public static String getSessionLocale(Context ctx) {
        return (String) ctx.sessionAttribute("locale");
    }

    public static String getSessionCurrentUser(Context ctx) {
        return (String) ctx.sessionAttribute("currentUser");
    }

    public static boolean removeSessionAttrLoggedOut(Context ctx) {
        String loggedOut = ctx.sessionAttribute("loggedOut");
        ctx.sessionAttribute("loggedOut", null);
        return loggedOut != null;
    }

    public static String removeSessionAttrLoginRedirect(Context ctx) {
        String loginRedirect = ctx.sessionAttribute("loginRedirect");
        ctx.sessionAttribute("loginRedirect", null);
        return loginRedirect;
    }

}
