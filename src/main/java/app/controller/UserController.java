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

import static app.Main.*;
import static app.controller.utils.RequestUtil.*;


public class UserController {

    public static Handler serveProfilePageGet = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        User user = userDao.getUserByUsername(getSessionCurrentUser(ctx));
        model.put("user", user);
        ctx.render(Template.USER, model);
    };

    public static Handler serveProfileEditPageGet = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        User user = userDao.getUserByUsername(getSessionCurrentUser(ctx));
        model.put("user", user);
        ctx.render(Template.USEREDIT, model);
    };

    public static Handler serveProfileEditPagePost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        User user = userDao.getUserByUsername(getSessionCurrentUser(ctx));
        user.updateUserInfo(getQueryFirstname(ctx), getQueryLastname(ctx), getQueryEmail(ctx), getQueryGender(ctx), getQueryTypeOfUser(ctx), getQueryCountry(ctx));
        model.put("saveSuccess", true);
        model.put("user", user);
        ctx.render(Template.USEREDIT, model);
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
        String hashedPassword = BCrypt.hashpw(password, user.salt);
        return hashedPassword.equals(user.hashedPassword);
    }

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

    public static void newUser(String username, String password, String firstname, String lastname, String email, String gender, String typeofuser, String country) {

        String newSalt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, newSalt);

        if (typeofuser.equals("regularUser")) {
            userDao.addRegularUser(new RegularUser(username, newSalt, hashedPassword, firstname, lastname, email, gender, country));
        } else if (typeofuser.equals("PCoUser")) {
            userDao.addPCOUser(new PCOUser(username, newSalt, hashedPassword, firstname, lastname, email));
        } else if (typeofuser.equals("criticsUser")) {
            userDao.addCriticsUser(new CriticsUser(username, newSalt, hashedPassword, firstname, lastname, email, gender, country));
        }

//        userDao.updateUsersList(user);
    }
}
