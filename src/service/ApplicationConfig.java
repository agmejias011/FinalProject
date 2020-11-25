/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Juan
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    @Override
    public Map<String, Object> getProperties() {
        return super.getProperties();
    }
    
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.ApplicationREST.class);
        resources.add(service.ClientREST.class);
        resources.add(service.HasTowerREST.class);
        resources.add(service.LoginREST.class);
        resources.add(service.LogoutREST.class);
        resources.add(service.ServiceREST.class);
        resources.add(service.TowerREST.class);
        resources.add(service.UserREST.class);
    }
}