/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import java.util.HashMap;
import java.util.List;
import util.Utility;

/**
 *
 * @author Juan
 */
public class ModelFacade {

    public ModelFacade() {
    }

    public String login(String email, String password) {
        String key;
        User user = new User();
        key = user.login(email, password);
        return key;
    }

    public boolean createClient(Client client) {
        return client.create();
    }

    public boolean createTower(Tower tower) {
        return tower.create();
    }

    public boolean createRequest(String token, String email, Service service, List<Tower> listTower) {
        return service.create(token, email, listTower);
    }

    public void acceptRequest(String token, String email, Integer serviceId, Integer towerId) {
        HasTower hasTower = new HasTower();
        hasTower.setServiceId(serviceId);
        hasTower.setTowerId(towerId);
        hasTower.acceptRequest(token, email);
    }

    public void declineRequest(String token, String email, Integer serviceId, Integer towerId) {
        HasTower hasTower = new HasTower();
        hasTower.setServiceId(serviceId);
        hasTower.setTowerId(towerId);
        hasTower.cancelRequest(token, email);
    }

    public User getUserByEmail(String token, String email) {
        User user = new User();
        RESTConnection conn = RESTConnection.getInstance();
        String path = Utility.USER_PATH;

        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("token", token);
        parameters.put("email", email);

        String json = conn.getMethod(path, parameters);

        List<User> list = user.fromJsonUser(json);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public void blockAccount(String email) {
        User user = new User();
        user.block(email);
    }

    public Client getClientIdByEmail(String token, String email) {
        Client client = new Client();
        return client.selectByEmailClient(token, email);
    }

    public boolean logout(String email, String token) {
        User user = new User();
        return user.logout(token, email);
    }

    public String getMenu(Integer userTypeId) {
        String menu = "";
        Application app = new Application();
        List<Application> list = app.getApplicationByUserTypeId(userTypeId);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                menu += "<li><a href='" + list.get(i).getUrl() + "'>" + list.get(i).getName() + "</a>";
            }
        }
        return menu;
    }

    public boolean updateTower(Tower obj, String token) {
        return obj.update(token);
    }

    public boolean updateClient(Client obj, String token) {
        return obj.update(token);
    }

    public boolean EmailNotExist(String email) {
        User user = new User();
        user.setEmail(email);
        return user.findByEmail();
    }

    public List<Service> selectService(String email, String token) {
        Service service = new Service();
        return service.selectClientService(email, token);
    }

    public List<Tower> selectTower(String email, String token) {
        Tower tower = new Tower();
        tower.setEmail(email);
        return tower.selectAll(token);
    }

}
