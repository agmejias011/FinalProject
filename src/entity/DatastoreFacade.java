/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import util.Location;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Juan
 */
public class DatastoreFacade {

    public DatastoreFacade() {
    }

    public boolean createClient(Client client) {
        return client.create();
    }

    public boolean createTower(Tower tower) {
        return tower.create();
    }

    public boolean createRequest(Service service, List<Tower> listTower) {
        return service.create(listTower);
    }

    public void acceptRequest(Integer serviceId, Integer towerId) {
        HasTower hasTower = new HasTower();
        hasTower.setServiceId(serviceId);
        hasTower.setTowerId(towerId);
        hasTower.acceptRequest();
    }

    public void declineRequest(Integer serviceId, Integer towerId) {
        HasTower hasTower = new HasTower();
        hasTower.setServiceId(serviceId);
        hasTower.setTowerId(towerId);
        hasTower.cancelRequest();
    }

    public boolean validateUser(String email, String password) {
        User user = new User();
        return user.validateUser(email, password);
    }

    public List<Service> selectServiceByTowerEmail(String towerEmail) {
        List<Service> list = null;
        Service service = new Service();
        Tower tower = new Tower();
        tower.setEmail(towerEmail);
        // List<Tower> listTower = tower.selectTowerByEmail(towerEmail);
        // if(listTower!=null && listTower.size()>0){
        list = service.selectServiceByEmail(towerEmail);
        // }
        return list;
    }

    public boolean chargeService(String email, Integer serviceId) {
        Payment payment = new Payment();
        payment.setAmount(80.0);
        Service service = new Service();
        service.setId(serviceId);
        service.setCost(80.0);
        service.setEndDate(new Date());
        service.endService();
        return payment.charge();
    }

    public boolean declineService(String towerEmail, Integer serviceId) {
        HasTower hasTower = new HasTower();
        Tower tower = new Tower();
        tower.setEmail(towerEmail);
        List<Tower> listTower = tower.selectTowerByEmail(towerEmail);

        if (listTower != null && listTower.size() > 0) {
            hasTower.setTowerId(listTower.get(0).getId());
        }
        hasTower.setServiceId(serviceId);
        hasTower.setTowerDeclineDate(new Date());
        return hasTower.cancelRequest();
    }

    public boolean acceptService(String towerEmail, Integer serviceId) {
        HasTower hasTower = new HasTower();
        Tower tower = new Tower();
        tower.setEmail(towerEmail);
        List<Tower> listTower = tower.selectTowerByEmail(towerEmail);
        if (listTower != null && listTower.size() > 0) {
            hasTower.setTowerId(listTower.get(0).getId());
        }
        hasTower.setServiceId(serviceId);
        hasTower.setTowerAcceptDate(new Date());
        return hasTower.acceptRequest();
    }

    public List<Service> selectAllService() {
        Service obj = new Service();
        return obj.selectAll();
    }

    public List<Tower> selectAllTower(Location location, Integer order) {
        List<Tower> list;
        Tower tower = new Tower();
        list = tower.selectAllOrdered(location, order);
        return list;
    }

    /*
     * public boolean requestService(String content, String email, Location
     * location) { Service service = new Service(); return service.create(null); }
     */
    public List<User> selectUserByEmail(String email) {
        List<User> list;
        User user = new User();
        list = user.selectByEmail(email);
        return list;
    }

    public List<Client> selectClientByEmail(String email) {
        List<Client> list;
        Client client = new Client();
        list = client.selectClientByEmail(email);
        return list;
    }

    public List<Tower> selectTowerByAddress(String address) {
        List<Tower> list = new ArrayList<Tower>();
        List<Tower> listAux;
        Tower tower = new Tower();
        listAux = tower.selectAll();

        for (int i = 0; i < listAux.size(); i++) {
            list.add(listAux.get(i));
            for (int j = 0; i < list.size(); i++) {

            }
        }
        return list;
    }

    public List<Tower> selectTowerByAddress(Location location, String city, String state) {
        List<Tower> list;
        Tower tower = new Tower();
        tower.setCity(city);
        tower.setState(state);
        list = tower.SelectByStateCity(location);

        return list;
    }

    public List<Tower> selectTowerByRating(String city, String state) {
        List<Tower> list = null;
        Tower tower = new Tower();
        tower.setCity(city);
        tower.setState(state);
        list = tower.SelectByRating();

        return list;
    }

    public List<Tower> selectTowerByPrice(String city, String state) {
        List<Tower> list = null;
        Tower tower = new Tower();
        tower.setCity(city);
        tower.setState(state);
        list = tower.SelectByPrice();

        return list;
    }

    public boolean updateClient(Client client) {
        return client.update();
    }

    public List<Application> selectApplication(Integer userTypeId) {
        Application app = new Application();
        return app.selectByUserTypeId(userTypeId);
    }

    public List<Service> selectUserByClientEmail(String email) {
        List<Service> list = null;
        Service service = new Service();
        Tower tower = new Tower();
        tower.setEmail(email);
        list = service.selectServiceByClientEmail(email);
        return list;
    }

    public void block(User user) {
        user.block();
    }

    public boolean updatePickup(String address, Integer serviceId) {
        Service service = new Service();
        return service.updatePickup(address, serviceId);
    }

    public boolean updateDestination(String address, Integer serviceId) {
        Service service = new Service();
        return service.updateDestination(address, serviceId);
    }

    public boolean makePayment(Double amount, Integer serviceId) {
        Payment payment = new Payment();
        return payment.makePayment(amount, serviceId);
    }

    public boolean rateTower(Integer towerId, Integer serviceId, Integer rating) {
        HasTower hasTower = new HasTower();
        return hasTower.rateTower(towerId, serviceId, rating);

    }

    public boolean updateTower(Tower tower) {
        return tower.update();
    }

    public Tower getTowerById(Integer id) {
        Tower tower = new Tower();
        return tower.selectById(id);
    }

    public Client getClientById(Integer id) {
        Client client = new Client();
        return client.selectById(id);
    }

}
