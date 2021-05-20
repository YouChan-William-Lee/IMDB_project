package app;

import app.controller.*;
import app.dao.*;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import app.controller.localisation.Filters;
import app.controller.utils.HerokuUtil;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;

import static io.javalin.apibuilder.ApiBuilder.before;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Main {
    public static ShowDao showDao;
    public static UserDao userDao;
    public static CastDao castDao;
    public static ProductionCoDao productionCoDao;
    public static UserReviewDao userReviewDao;
    public static UserFeedbackDao userFeedbackDao;
    public static UserRatingDao userRatingDao;

    public static void main(String[] args) {
        // Instantiate your dependencies
        initialization();

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        }).start(HerokuUtil.getHerokuAssignedPort());

        //Serve index page when user firstly enters to IMDB
        app.get("/", IndexController.serveIndexPage);

        app.routes(() -> {
            before(Filters.handleLocaleChange);
            get(Web.INDEX, IndexController.serveIndexPage);
            get(Web.SHOWS, ShowController.fetchAllShows);
            get(Web.ONE_SHOW, ShowController.fetchOneShow);
            get(Web.LOGIN, LoginController.serveLoginPage);
            get(Web.SIGNIN, SigninController.serveSigninPage);
            get(Web.USER, UserController.serveProfilePageGet);
            get(Web.USERADDSHOW, ShowController.fetchAddNewPage);
            get(Web.ADMINEDITSHOW, ShowController.fetchEditShowPage);
            post(Web.LOGIN, LoginController.handleLoginPost);
            post(Web.LOGOUT, LoginController.handleLogoutPost);
            post(Web.SIGNIN, SigninController.handleSigninPost);
            post(Web.SEARCH, ShowController.fetchSearchedShowsPost);
            post(Web.USERADDSHOW, ShowController.fetchAddNewPagePost);
            post(Web.ONE_SHOW, ShowController.fetchDeleteShowPost);
            post(Web.USER, UserController.serveProfilePageGetPost);
            post(Web.ADMINEDITSHOW, ShowController.fetchEditShowPagePost);
            post(Web.USERRATE,UserRatingController.handleRatingPost);
            post(Web.USERREVIEW,UserReviewController.handleReviewPost);
            post(Web.USERFEEDBACK,UserFeedbackController.handleFeedbackPost);
        });

        app.error(404, ViewUtil.notFound);
    }

    public static void initialization() {
        showDao = new ShowDao();
        userDao = new UserDao();
        castDao = new CastDao();
        productionCoDao = new ProductionCoDao();
        userReviewDao = new UserReviewDao();
        userFeedbackDao = new UserFeedbackDao();
        userRatingDao = new UserRatingDao();
        //Connect to database
        Database.startConnection();
    }
}
