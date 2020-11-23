/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appLogic;

import entity.DatastoreFacade;
import entity.Service;
import entity.Tower;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.Location;
import util.Utility;

/**
 *
 * @author Juan
 */
public class ServiceLogic {

    private final Authenticator authenticator;
    private DatastoreFacade ds;

    public ServiceLogic() {
        authenticator = Authenticator.getInstance();
        ds = new DatastoreFacade();
    }
    
    public void setDatastoreFacade(DatastoreFacade ds){
        this.ds = ds;
    }

    public Boolean createService(String content, String token, String email) {
        Boolean resp = false;
        if (authenticator.isAuthTokenValid(token, email)) {
            //DatastoreFacade ds = new DatastoreFacade();
            String pickup;
            String destination;
            String[] array = getObjects(content);
            String jsonService = array[0];
            String jsonTower = array[1];

            List<Tower> listTower = Tower.fromJson(jsonTower);
            List<Service> list = Service.fromJson(jsonService);
            Service service = null;

            for (int i = 0; i < list.size(); i++) {
                try {
                    service = list.get(i);
                    pickup = service.getStreetAddressPickup() + ", " + service.getCityPickup() + ", " + service.getStatePickup() + " " + service.getZipcodePickup();
                    destination = service.getStreetAddressDestination() + ", " + service.getCityDestination() + ", " + service.getStateDestination() + " " + service.getZipcodeDestination();
                    Location locationPickup = null;//Utility.getLocationFromAddress(pickup);
                    Location locationDestination = null;//Utility.getLocationFromAddress(destination);
                    if (locationPickup != null) {
                        service.setLatitudePickup(locationPickup.getLatitude());
                        service.setLongitudePickup(locationPickup.getLongitude());
                    }
                    if (locationDestination != null) {
                        service.setLatitudeDestination(locationDestination.getLatitude());
                        service.setLongitudeDestination(locationDestination.getLongitude());
                    }
                    //return service.create(listTower);
                } catch (Exception ex) {
                    Logger.getLogger(DatastoreFacade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //return false;
            if (ds.createRequest(service, listTower)) {
                resp = true;
            }
        }
        return resp;
    }

    //Splits objects between [Object1] [Object2] [Object3]
    private String[] getObjects(String content) {
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(content);
        ArrayList<String> stringList = new ArrayList<String>();

        while (matcher.find()) {
            stringList.add(matcher.group(1));
        }

        String[] stringArray = stringList.toArray(new String[stringList.size()]);
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = "[" + stringArray[i] + "]";
        }
        return stringArray;
    }

    public List<Service> selectServiceByTowerEmail(String authToken, String towerEmail) {
        List<Service> list = null;
        if (authenticator.isAuthTokenValid(authToken, towerEmail)) {
            //DatastoreFacade ds = new DatastoreFacade();
            list = ds.selectServiceByTowerEmail(towerEmail);
        }
        return list;
    }

    public boolean chargeClient(String content, String authToken, String email, Integer serviceId) {
        if (authenticator.isAuthTokenValid(authToken, email)) {
            //DatastoreFacade ds = new DatastoreFacade();
            return ds.chargeService(email, serviceId);
        }
        return false;
    }

    public boolean declineService(String content, String authToken, String email, Integer serviceId) {
        if (authenticator.isAuthTokenValid(authToken, email)) {
            //DatastoreFacade ds = new DatastoreFacade();
            return ds.declineService(email, serviceId);
        }
        return false;
    }

    public List<Tower> listTower(String authToken, String email, String pickup, Integer order) {
        if (authenticator.isAuthTokenValid(authToken, email)) {
            //DatastoreFacade ds = new DatastoreFacade();
            Location location = null;
            try {
                location = null;//Utility.getLocationFromAddress(pickup);
            } catch (Exception ex) {
                Logger.getLogger(AppLogicFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ds.selectAllTower(location, order);
        }
        return null;
    }

    public List<Service> selectAllService(String authToken, String email) {
        if (authenticator.isAuthTokenValid(authToken, email)) {
            //DatastoreFacade ds = new DatastoreFacade();
            return ds.selectAllService();
        }
        return null;
    }

    public boolean acceptService(String content, String authToken, String email, Integer serviceId) {
        if (authenticator.isAuthTokenValid(authToken, email)) {
            //DatastoreFacade ds = new DatastoreFacade();
            if (ds.acceptService(email, serviceId)) {
                return true;
            }
        }
        return false;
    }

    public boolean updatePickup(String token, String email, Integer serviceId, String pickup) {
        boolean resp = false;
        if (authenticator.isAuthTokenValid(token, email)) {
            //DatastoreFacade ds = new DatastoreFacade();
            if (ds.updatePickup(email, serviceId)) {
                resp = true;
            }
        }
        return resp;
    }

    public boolean updateDestination(String token, String email, Integer serviceId, String destination) {
        boolean resp = false;
        if (authenticator.isAuthTokenValid(token, email)) {
            //DatastoreFacade ds = new DatastoreFacade();
            if (ds.updateDestination(destination, serviceId)) {
                resp = true;
            }
        }
        return resp;
    }

    public boolean makePayment(String token, String email, Integer serviceId, Double payment) {
        boolean resp = false;
        if (authenticator.isAuthTokenValid(token, email)) {
            //DatastoreFacade ds = new DatastoreFacade();
            if (ds.makePayment(payment, serviceId)) {
                resp = true;
            }
        }
        return resp;
    }

    public boolean rateTower(String token, String email, Integer serviceId, Integer towerId, Integer rate) {
        boolean resp = false;
        if (authenticator.isAuthTokenValid(token, email)) {
            //DatastoreFacade ds = new DatastoreFacade();
            if (ds.rateTower(towerId, serviceId, rate)) {
                resp = true;
            }
        }
        return resp;
    }

}
