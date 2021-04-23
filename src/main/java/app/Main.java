package app;

import app.controller.SigninController;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import app.dao.ShowDao;
import app.dao.UserDao;
import app.controller.IndexController;
import app.controller.LoginController;
import app.controller.ShowController;
import app.controller.localisation.Filters;
import app.controller.utils.HerokuUtil;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;

import java.sql.*;

import static io.javalin.apibuilder.ApiBuilder.before;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Main {
    public static ShowDao showDao;
    public static UserDao userDao;
    public static Connection conn;

    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/imdb";
        String user = "root";
        String password = "jainamdoshi";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            System.out.println("Connection: " + url);

//            String query = "Insert into student(id, name) values(101, 'ram')";
            Statement statement = connection.createStatement();
//            statement.execute(query);
            ResultSet rs = statement.executeQuery("SELECT * FROM person");
//            rs.beforeFirst();
            rs.next();
            System.out.println(rs.getString(3));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }


//        Javalin app = Javalin.create().start(3000);
//        app.get("/", ctx -> ctx.result("Hello world!"));

//        try {
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb?" + "user=root&password=jainamdoshi");
//
////            conn.setSchema("Test");
//
//            Statement stmt = conn.createStatement();
//            ResultSet rs;
//
//            System.out.println("Connection: " + conn.getSchema());
//
////            if (stmt.execute("SELECT * FROM person")) {
////                rs = stmt.getResultSet();
////                System.out.println("Connection: " + rs);
////            }
//            // Do something with the Connection
//
//        } catch (SQLException ex) {
//            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }


        // Instantiate your dependencies
        showDao = new ShowDao();
        userDao = new UserDao();

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
            post(Web.LOGIN, LoginController.handleLoginPost);
            post(Web.LOGOUT, LoginController.handleLogoutPost);
            post(Web.SIGNIN, SigninController.handleSigninPost);
        });

        app.error(404, ViewUtil.notFound);
    }
}
