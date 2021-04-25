package app;

import app.controller.*;
import app.dao.CastDao;
import app.dao.Model;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import app.dao.ShowDao;
import app.dao.UserDao;
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

    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/imdb";
        String user = "root";
        String password = "jainamdoshi";

        Model.connectToDatabase(url, user, password);

        // Instantiate your dependencies
        showDao = new ShowDao();
        userDao = new UserDao();
        castDao = new CastDao();

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        }).start(HerokuUtil.getHerokuAssignedPort());

        app.routes(() -> {
            before(Filters.handleLocaleChange);
            before(LoginController.ensureLoginBeforeViewingShows);
            get(Web.INDEX, IndexController.serveIndexPage);
            get(Web.SHOWS, ShowController.fetchAllShows);
            get(Web.ONE_SHOW, ShowController.fetchOneShow);
            get(Web.LOGIN, LoginController.serveLoginPage);
            get(Web.SIGNIN, SigninController.serveSigninPage);
            get(Web.USER, UserController.serveProfilePageGet);
            get(Web.USEREDIT, UserController.serveProfileEditPageGet);
            post(Web.LOGIN, LoginController.handleLoginPost);
            post(Web.LOGOUT, LoginController.handleLogoutPost);
            post(Web.SIGNIN, SigninController.handleSigninPost);
            post(Web.USEREDIT, UserController.serveProfileEditPagePost);
        });

        app.error(404, ViewUtil.notFound);
    }
}
