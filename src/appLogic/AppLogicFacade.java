/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appLogic;

import entity.Application;
import entity.Client;
import entity.DatastoreFacade;
import entity.Service;
import entity.Tower;
import entity.User;
import java.util.List;
import util.Location;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import util.Utility;

/**
 *
 * @author Juan
 */
public class AppLogicFacade {

    private final Authenticator authenticator;

    public AppLogicFacade() {
        authenticator = Authenticator.getInstance();
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public String login(String email, String password) throws LoginException {
        LoginLogic logic = new LoginLogic();
        return logic.login(email, password);
    }

    /* Handles Logout for Client and Tower
     * Returns true or false
     */
    public boolean logout(String token, String email) {
        LogoutLogic logic = new LogoutLogic();
        return logic.logout(token, email);
    }

    /* Handles Login for Client and Tower
     * Returns true or false
     */
    public boolean registerClient(String content) {
        RegistrationLogic logic = new RegistrationLogic();
        return logic.registerClient(content);
    }

    /* Handles Login for Client and Tower
     * Returns true or false
     */
    public boolean registerTower(String content) {
        RegistrationLogic registration = new RegistrationLogic();
        return registration.registerTower(content);
    }

    /* Handles Login for Client and Tower
     * Returns true or false
     */
    public boolean updateClient(String content, String token, String email) {
        EditProfileLogic editProfile = new EditProfileLogic();
        return editProfile.updateClient(content, email, token);
    }

    /* Handles Login for Client and Tower
     * Returns true or false
     */
    public boolean updateTower(String content, String email, String token, Integer id) {
        EditProfileLogic editProfile = new EditProfileLogic();
        return editProfile.updateTower(content, email, token, id);
    }

    /* Handles Login for Client and Tower
     * Returns true or false
     */
    public boolean updatePickup(String token, String email, Integer serviceId, String pickup) {
        ServiceLogic service = new ServiceLogic();
        return service.updatePickup(token, email, serviceId, pickup);
    }

    /* Handles Login for Client and Tower
     * Returns true or false
     */
    public boolean updateDestination(String token, String email, Integer serviceId, String destination) {
        ServiceLogic service = new ServiceLogic();
        return service.updateDestination(token, email, serviceId, destination);
    }

    /* Handles Login for Client and Tower
     * Returns true or false
     */
    public Boolean requestService(String content, String token, String email) {
        ServiceLogic obj = new ServiceLogic();
        return obj.createService(content, token, email);
    }

    /* Handles Login for Client and Tower
     * Returns List of Towers
     */
    public List<Tower> listTower(String authToken, String email, String pickup, Integer order) {
        ServiceLogic service = new ServiceLogic();
        return service.listTower(authToken, email, pickup, order);
    }

    /* Handles Login for Client and Tower
     * Returns Location{latitude,longitude}
     */
    public Location getLocationByAddress(String address) {
        Location location = null;
        try {
            location = Utility.getLocationFromAddress(address);
        } catch (Exception ex) {
            Logger.getLogger(AppLogicFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return location;
    }

    /* Handles request of List of Services made by Towers
     * Returns List of Services Availables for Towers
     */
    public List<Service> selectAllService(String authToken, String email) {
        ServiceLogic service = new ServiceLogic();
        return service.selectAllService(authToken, email);
    }

    /* Handles Acceptance of Service made by Towers
     * Returns valid token for future transactions
     */
    public boolean acceptService(String content, String authToken, String email, Integer serviceId) {
        ServiceLogic service = new ServiceLogic();
        return service.acceptService(content, authToken, email, serviceId);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public boolean declineService(String content, String authToken, String email, Integer serviceId) {
        ServiceLogic service = new ServiceLogic();
        return service.declineService(content, authToken, email, serviceId);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public boolean chargeClient(String content, String authToken, String email, Integer serviceId) {
        ServiceLogic service = new ServiceLogic();
        return service.chargeClient(content, authToken, email, serviceId);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public List<Service> selectServiceByTowerEmail(String authToken, String towerEmail) {
        ServiceLogic service = new ServiceLogic();
        return service.selectServiceByTowerEmail(authToken, towerEmail);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public String getAddressByLocation(Location location) {
        return Utility.getAddressByLocation(location);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public boolean makePayment(String token, String email, Integer serviceId, Double payment) {
        ServiceLogic service = new ServiceLogic();
        return service.makePayment(token, email, serviceId, payment);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public boolean rateTower(String token, String email, Integer serviceId, Integer towerId, Integer rate) {
        ServiceLogic service = new ServiceLogic();
        return service.rateTower(token, email, serviceId, towerId, rate);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public void blockUser(String email) {
        LoginLogic login = new LoginLogic();
        login.block(email);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public List<User> selectUserByEmail(String email, String token) {
        LoginLogic login = new LoginLogic();
        return login.selectUserByEmail(email, token);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public List<Client> selectClientByEmail(String email, String token) {
        EditProfileLogic editProfile = new EditProfileLogic();
        return editProfile.selectClientByEmail(email, token);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public List<Tower> getTowerList(String token, String email, String address, Integer order) {
        ListTowerLogic logic = new ListTowerLogic();
        return logic.getTowerList(token, email, address, order);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public List<Tower> getTowerById(String token, String email, Integer id) {
        EditProfileLogic editProfile = new EditProfileLogic();
        return editProfile.getTowerById(email, token, id);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public List<Client> getClientById(String token, String email, Integer id) {
        EditProfileLogic editProfile = new EditProfileLogic();
        return editProfile.getClientById(email, token, id);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public List<Application> selectApplication(Integer userTypeId) {
        DatastoreFacade ds = new DatastoreFacade();
        return ds.selectApplication(userTypeId);
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public boolean findUserByEmail(String email) {
        DatastoreFacade ds = new DatastoreFacade();
        return ds.selectUserByEmail(email).isEmpty();
    }

    /* Handles Login for Client and Tower
     * Returns valid token for future transactions
     */
    public List<Service> selectAllServiceByClient(String authToken, String email) {
        DatastoreFacade ds = new DatastoreFacade();
        return ds.selectUserByClientEmail(email);
    }

}
