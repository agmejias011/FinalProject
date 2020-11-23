/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appLogic;

import entity.Client;
import entity.DatastoreFacade;
import entity.Tower;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class EditProfileLogic {

    private Authenticator authenticator;
    private DatastoreFacade ds;

    public EditProfileLogic() {
        authenticator = Authenticator.getInstance();
        ds = new DatastoreFacade();
    }

    public void setDatastoreFacade(DatastoreFacade ds) {
        this.ds = ds;
    }

    public boolean updateTower(String content, String email, String token, Integer id) {
        boolean resp = false;
        if (authenticator.isAuthTokenValid(token, email)) {
            List<Tower> list;
            Tower tower;
            list = Tower.fromJson(content);
            if (list != null && list.size() > 0) {
                tower = list.get(0);
                tower.setId(id);
                resp = ds.updateTower(tower);
            }
        }
        return resp;
    }

    public List<Tower> getTowerById(String email, String token, Integer id) {
        List<Tower> list = new ArrayList<Tower>();
        if (authenticator.isAuthTokenValid(token, email)) {
            list.add(ds.getTowerById(id));
        }
        return list;
    }

    public List<Client> getClientById(String email, String token, Integer id) {
        List<Client> list = new ArrayList<Client>();
        
        if (authenticator.isAuthTokenValid(token, email)) {
            list.add(ds.getClientById(id));
        }
        return list;
    }

    public boolean updateClient(String content, String email, String token) {
        boolean resp = false;
        if (authenticator.isAuthTokenValid(token, email)) {
            //DatastoreFacade ds = new DatastoreFacade();
            Client client = new Client();
            List<Client> list = client.fromJson(content);
            if (list.size() > 0) {
                resp = ds.updateClient(list.get(0));
            }
        }
        return resp;
    }

    List<Client> selectClientByEmail(String email, String token) {
        List<Client> list = null;
        if (authenticator.isAuthTokenValid(token, email)) {
            //DatastoreFacade ds = new DatastoreFacade();
            list = ds.selectClientByEmail(email);
        }
        return list;
    }

}
