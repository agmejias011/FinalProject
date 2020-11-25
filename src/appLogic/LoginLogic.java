/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appLogic;

import entity.DatastoreFacade;
import entity.User;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;

/**
 *
 * @author Juan
 */
public class LoginLogic {

    private final Authenticator authenticator;
    private DatastoreFacade ds;

    public LoginLogic() {
        authenticator = Authenticator.getInstance();
        ds = new DatastoreFacade();
    }

    public void setDatastoreFacade(DatastoreFacade ds) {
        this.ds = ds;
    }

    String login(String email, String password) {
        String token = "";
        try {
            token = authenticator.login(email, password);
        } catch (LoginException ex) {
            Logger.getLogger(LoginLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return token;
    }

    public void block(String email) {
        User user = new User();
        List<User> list = user.selectByEmail(email);
        if (list.size() > 0) {
            user = list.get(0);
        }
        user.setBlocked(UUID.randomUUID().toString());
        //DatastoreFacade ds = new DatastoreFacade();
        ds.block(user);
    }

    public List<User> selectUserByEmail(String email, String token) {
        List<User> list = null;
        if (authenticator.isAuthTokenValid(token, email)) {
            //DatastoreFacade ds = new DatastoreFacade();
            list = ds.selectUserByEmail(email);
        }
        return list;
    }

}
