/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appLogic;

import entity.DatastoreFacade;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.security.auth.login.LoginException;

/**
 *
 * @author Juan
 */
public class Authenticator {

    private static final Authenticator authenticator = new Authenticator();

    // A user storage which stores <username, password>
    //private final Map<String, String> usersStorage = new HashMap();
    //AppLogicFacade appLogic = new AppLogicFacade();
    // A service key storage which stores <service_key, username>
    //private final Map<String, String> serviceKeysStorage = new HashMap();
    // An authentication token storage which stores <service_key, auth_token>.
    private static Map<String, String> authorizationTokensStorage = new HashMap<String, String>();

    private Authenticator() {
        // The usersStorage pretty much represents a user table in the database
        //authorizationTokensStorage.put("8336ed2d-9ade-4139-993e-55c56094d3be", "user1@email.com");
        //authorizationTokensStorage.put("12c99426-b051-4db1-ba7d-2e7f3de9f7a3", "user2@email.com");
        //usersStorage.put("username1", "passwordForUser1");
        //usersStorage.put("username2", "passwordForUser2");
        //usersStorage.put("username3", "passwordForUser3");

        /**
         * Service keys are pre-generated by the system and is given to the
         * authorized client who wants to have access to the REST API. Here,
         * only username1 and username2 is given the REST service access with
         * their respective service keys.
         */
        //serviceKeysStorage.put("f80ebc87-ad5c-4b29-9366-5359768df5a1", "username1");
        //serviceKeysStorage.put("3b91cab8-926f-49b6-ba00-920bcf934c2a", "username2");
    }

    public static Authenticator getInstance() {
        /*if (authenticator == null) {
            authenticator = new Authenticator();
        }*/

        return authenticator;
    }

    public String login(String email, String password) throws LoginException {
        /*if (serviceKeysStorage.containsKey(serviceKey)) {
            String usernameMatch = serviceKeysStorage.get(serviceKey);

            if (usernameMatch.equals(email) && usersStorage.containsKey(email)) {*/
        //String passwordMatch = usersStorage.get(email);
        DatastoreFacade obj = new DatastoreFacade();
        if (obj.validateUser(email, password)) {

            /**
             * Once all params are matched, the authToken will be generated and
             * will be stored in the authorizationTokensStorage. The authToken
             * will be needed for every REST API invocation and is only valid
             * within the login session
             */
            String authToken = UUID.randomUUID().toString();
            authorizationTokensStorage.put(authToken, email);

            return authToken;
        }

        throw new LoginException("LoginException");
    }

    /**
     * The method that pre-validates if the client which invokes the REST API is
     * from a authorized and authenticated source.
     *
     * @param email
     * @param authToken The authorization token generated after login
     * @return TRUE for acceptance and FALSE for denied.
     */
    public boolean isAuthTokenValid(String authToken, String email) {

        if (authorizationTokensStorage.containsKey(authToken)
                && authorizationTokensStorage.get(authToken).equals(email)) {
            return true;
        }

        return false;
    }
    
    public boolean isEmailValid(String email){
        return authorizationTokensStorage.containsValue(email);
    }
    
    //Remove all the value pairs that matches with value == email
    public void logout(String authToken, String email) throws GeneralSecurityException {
        /*if(authorizationTokensStorage.containsValue(email)){
            //authorizationTokensStorage.values().removeAll(email);
            return;
        }*/
        if (authorizationTokensStorage.containsKey(authToken)) {

            /**
             * When a client logs out, the authentication token will be remove
             * and will be made invalid.
             */
            authorizationTokensStorage.remove(authToken);
            return;
        }

        throw new GeneralSecurityException("Invalid service key and authorization token match.");
    }

}
