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

    public static void main(String[] args) {


        // Instantiate your dependencies
        showDao = new ShowDao();
        userDao = new UserDao();
        castDao = new CastDao();
        productionCoDao = new ProductionCoDao();

        Database.startConnection();

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        }).start(HerokuUtil.getHerokuAssignedPort());

        app.get("/", IndexController.serveIndexPage);

        app.routes(() -> {
            before(Filters.handleLocaleChange);
//            before(LoginController.ensureLoginBeforeViewingShows);
            get(Web.INDEX, IndexController.serveIndexPage);
            get(Web.SHOWS, ShowController.fetchAllShows);
            get(Web.ONE_SHOW, ShowController.fetchOneShow);
            get(Web.LOGIN, LoginController.serveLoginPage);
            get(Web.SIGNIN, SigninController.serveSigninPage);
            get(Web.USER, UserController.serveProfilePageGet);
            get(Web.ADDMINADDSHOW, ShowController.fetchAddNewPage);
            post(Web.LOGIN, LoginController.handleLoginPost);
            post(Web.LOGOUT, LoginController.handleLogoutPost);
            post(Web.SIGNIN, SigninController.handleSigninPost);
            post(Web.SEARCH, ShowController.fetchSearchedShowsPost);
            post(Web.ADDMINADDSHOW, ShowController.fetchAddNewPagePost);
        });

        app.error(404, ViewUtil.notFound);
    }
}
