package app.controller;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.model.Users.CriticsUser;
import app.model.Users.PCOUser;
import app.model.Users.RegularUser;
import io.javalin.http.Handler;
import java.util.Map;
import app.model.Users.User;
import org.mindrot.jbcrypt.BCrypt;
import app.model.ShowEntities.Show;

import static app.Main.*;
import static app.controller.utils.RequestUtil.*;


public class UserController {

    // Get user's information
    public static Handler serveProfilePageGet = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        User user = userDao.getUserByUsername(getSessionCurrentUser(ctx));
        model.put("user", user);
        model.put("users", userDao.getAllUsers());
        model.put("shows", showDao.getAllShows());
        ctx.render(Template.USER, model);
    };
    // Approve criticuser
    public static Handler serveProfilePageGetPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        // If admin press approve button on user
        if(getParamApproveUser(ctx) != null) {
            User approvedCriticUser = userDao.getUserByUsername(getParamApproveUser(ctx));
            approvedCriticUser.setApproved(true);
            userDao.updateUserToDatabase(approvedCriticUser);
            model.put("UserApproved", true);
            model.put("ApprovedUser", getParamApproveUser(ctx));
        }

        // If admin press reject button on user
        if(getParamRejectUser(ctx) != null) {
            User rejectedCriticUser = userDao.getUserByUsername(getParamRejectUser(ctx));
            userDao.deleteUserToDatabase(rejectedCriticUser);
            model.put("UserApproved", false);
            model.put("RejectedUser", getParamRejectUser(ctx));
        }

        // If admin press approve button on show
        if(getParamApproveShow(ctx) != null) {
            Show approvedShow = showDao.getShowByShowTitle(getParamApproveShow(ctx));
            approvedShow.setApproved(true);
            showDao.updateShow(approvedShow);
            model.put("ShowApproved", true);
            model.put("ApprovedShow", getParamApproveShow(ctx));
        }

        // If admin press reject button on show
        if(getParamRejectShow(ctx) != null) {
            Show rejectedShow = showDao.getShowByShowTitle(getParamRejectShow(ctx));
            showDao.deleteShow(rejectedShow);
            model.put("ShowApproved", false);
            model.put("RejectedShow", getParamRejectShow(ctx));
        }

        User user = userDao.getUserByUsername(getSessionCurrentUser(ctx));
        model.put("shows", showDao.getAllShows());
        model.put("user", user);
        model.put("users", userDao.getAllUsers());
        ctx.render(Template.USER, model);
    };

    // Authenticate the user by hashing the inputted password using the stored salt,
    // then comparing the generated hashed password to the stored hashed password
    public static boolean authenticate(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        String hashedPassword = BCrypt.hashpw(password, user.getSalt());
        return hashedPassword.equals(user.getHashedPassword());
    }

    //username is null -> false 
    //duplicated -> false
    //not duplicated -> true
    public static boolean duplicationCheck(String username) {
        if (username == null) {
            return false;
        }
        User user = userDao.getUserByUsername(username);
        return user == null;
    }


    // This method doesn't do anything, it's just included as an example
    public static void setPassword(String username, String oldPassword, String newPassword) {
        if (authenticate(username, oldPassword)) {
            String newSalt = BCrypt.gensalt();
            String newHashedPassword = BCrypt.hashpw(newSalt, newPassword);
            // Update the user salt and password
        }
    }

    //create a new user, now the new user is stored in database instead of buffer, it means if the server is closed, new user will not disappear when open server next time.
    public static void newUser(String username, String password, String firstname, String lastname, String email, String gender, String typeOfUser, String country) {

        String newSalt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, newSalt);

        if (typeOfUser.equals("regularUser")) {
            userDao.addUserToDatabase(new RegularUser(username, newSalt, hashedPassword, firstname, lastname, email, gender, country, "regular", true));
        } else if (typeOfUser.equals("PCoUser")) {
            userDao.addUserToDatabase(new PCOUser(username, newSalt, hashedPassword, firstname, lastname, email, "pco", false));
        } else if (typeOfUser.equals("criticsUser")) {
            userDao.addUserToDatabase(new CriticsUser(username, newSalt, hashedPassword, firstname, lastname, email, gender, country, "critics", false));
        }
    }
}
