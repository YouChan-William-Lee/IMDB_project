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

    // Get user's information
    public static Handler serveProfilePageGet = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        User user = userDao.getUserByUsername(getSessionCurrentUser(ctx));
        model.put("user", user);
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
            userDao.addUserToDatabase(new RegularUser(username, newSalt, hashedPassword, firstname, lastname, email, gender, country, "regular"));
        } else if (typeOfUser.equals("PCoUser")) {
            userDao.addUserToDatabase(new PCOUser(username, newSalt, hashedPassword, firstname, lastname, email, "pco"));
        } else if (typeOfUser.equals("criticsUser")) {
            userDao.addUserToDatabase(new CriticsUser(username, newSalt, hashedPassword, firstname, lastname, email, gender, country, "critics"));
        }
    }
}
