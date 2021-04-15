package app;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class Main {

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(3000);
        app.get("/", ctx -> ctx.result("Hello world!"));

        // Instantiate your dependencies
//        bookDao = new BookDao();
//        userDao = new UserDao();

//        Javalin app = Javalin.create(config -> {
//            config.addStaticFiles("/public");
//            config.registerPlugin(new RouteOverviewPlugin("/routes"));
//        }).start(HerokuUtil.getHerokuAssignedPort());
//
//        app.routes(() -> {
//            before(Filters.handleLocaleChange);
//            before(LoginController.ensureLoginBeforeViewingBooks);
//            get(Web.INDEX, IndexController.serveIndexPage);
//            get(Web.BOOKS, BookController.fetchAllBooks);
//            get(Web.ONE_BOOK, BookController.fetchOneBook);
//            get(Web.LOGIN, LoginController.serveLoginPage);
//            post(Web.LOGIN, LoginController.handleLoginPost);
//            post(Web.LOGOUT, LoginController.handleLogoutPost);
//        });
//
//        app.error(404, ViewUtil.notFound);
    }
}
