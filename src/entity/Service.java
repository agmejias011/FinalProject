/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Location;
import util.Utility;
import util.Utility.JsonDateDeserializer;

/**
 *
 * @author Juan
 */
public class Service {

    private Integer id;
    private Integer clientId;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
    private Date cancelDate;
    private Double cost;
    private Double latitudePickup;
    private Double longitudePickup;
    private Double latitudeDestination;
    private Double longitudeDestination;
    private String streetAddressPickup;
    private String cityPickup;
    private String statePickup;
    private String zipcodePickup;
    private String streetAddressDestination;
    private String cityDestination;
    private String stateDestination;
    private String zipcodeDestination;
    private String clientDescription;
    private String towerDescription;

    public Service() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getLatitudePickup() {
        return latitudePickup;
    }

    public void setLatitudePickup(Double latitudePickup) {
        this.latitudePickup = latitudePickup;
    }

    public Double getLongitudePickup() {
        return longitudePickup;
    }

    public void setLongitudePickup(Double longitudePickup) {
        this.longitudePickup = longitudePickup;
    }

    public Double getLatitudeDestination() {
        return latitudeDestination;
    }

    public void setLatitudeDestination(Double latitudeDestination) {
        this.latitudeDestination = latitudeDestination;
    }

    public Double getLongitudeDestination() {
        return longitudeDestination;
    }

    public void setLongitudeDestination(Double longitudeDestination) {
        this.longitudeDestination = longitudeDestination;
    }

    public String getStreetAddressPickup() {
        return streetAddressPickup;
    }

    public void setStreetAddressPickup(String streetAddressPickup) {
        this.streetAddressPickup = streetAddressPickup;
    }

    public String getStatePickup() {
        return statePickup;
    }

    public void setStatePickup(String statePickup) {
        this.statePickup = statePickup;
    }

    public String getZipcodePickup() {
        return zipcodePickup;
    }

    public void setZipcodePickup(String zipcodePickup) {
        this.zipcodePickup = zipcodePickup;
    }

    public String getStreetAddressDestination() {
        return streetAddressDestination;
    }

    public void setStreetAddressDestination(String streetAddressDestination) {
        this.streetAddressDestination = streetAddressDestination;
    }

    public String getStateDestination() {
        return stateDestination;
    }

    public void setStateDestination(String stateDestination) {
        this.stateDestination = stateDestination;
    }

    public String getZipcodeDestination() {
        return zipcodeDestination;
    }

    public void setZipcodeDestination(String zipcodeDestination) {
        this.zipcodeDestination = zipcodeDestination;
    }

    public String getClientDescription() {
        return clientDescription;
    }

    public void setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
    }

    public String getTowerDescription() {
        return towerDescription;
    }

    public void setTowerDescription(String towerDescription) {
        this.towerDescription = towerDescription;
    }

    public String getCityPickup() {
        return cityPickup;
    }

    public void setCityPickup(String cityPickup) {
        this.cityPickup = cityPickup;
    }

    public String getCityDestination() {
        return cityDestination;
    }

    public void setCityDestination(String cityDestination) {
        this.cityDestination = cityDestination;
    }

    public boolean create(List<Tower> listTower) {
        boolean resp = false;
        int parameterIndex = 0;
        int serviceId;
        HasTower rel = new HasTower();

        String sql = "INSERT INTO service (client_id, creation_date, start_date, end_date, cancel_date, cost,"
                + " latitude_pickup, longitude_pickup, latitude_destination, longitude_destination,"
                + " street_address_pickup, city_pickup, state_pickup, zipcode_pickup,"
                + " street_address_destination, city_destination, state_destination, zipcode_destination,"
                + " client_description, tower_description)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setPreparedStatement(sql);
            prepareStatement(db);
            serviceId = db.ExecuteNonQuery();
            this.setId(serviceId);

            //Create Relational Class Between Service and Tower
            for (int i = 0; i < listTower.size(); i++) {
                rel.setServiceId(serviceId);
                rel.setTowerId(listTower.get(i).getId());
                rel.create();
            }

            resp = true;
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (db != null) {
                try {
                    db.Close();
                } catch (SQLException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return resp;
    }

    public boolean endService() {
        boolean resp = false;
        int parameterIndex = 0;

        String sql = "UPDATE service SET tower_description=?, end_date=?, cost=? WHERE id=?";

        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setPreparedStatement(sql);
            db.getPreparedStatement().setString(++parameterIndex, this.getTowerDescription());
            db.getPreparedStatement().setDate(++parameterIndex, new java.sql.Date(this.getEndDate().getTime()));
            db.getPreparedStatement().setDouble(++parameterIndex, this.getCost()!=null?this.getCost():Types.DOUBLE);
            db.getPreparedStatement().setInt(++parameterIndex, this.getId());
            db.ExecuteNonQuery();
            resp = true;
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (db != null) {
                try {
                    db.Close();
                } catch (SQLException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return resp;
    }

    public List<Service> selectAll() {
        List<Service> list = new ArrayList<Service>();
        String sql;
        ResultSet rs = null;

        sql = "SELECT id, client_id, creation_date, start_date, end_date, cancel_date, cost,"
                + " latitude_pickup, longitude_pickup, latitude_destination, longitude_destination,"
                + " street_address_pickup, city_pickup, state_pickup, zipcode_pickup,"
                + " street_address_destination, city_destination, state_destination, zipcode_destination,"
                + " client_description, tower_description"
                + " FROM service s"
                + " WHERE id in ( SELECT service_id FROM has_tower WHERE (tower_accept_date is null) OR (tower_accept_date is not null AND tower_decline_date is not null) )";

        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            //System.out.println("Service: "+sql);
            while (rs.next()) {
                Service service = readResulset(rs);
                list.add(service);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
            try {
                db.Close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }

        return list;
    }

    private Service readResulset(ResultSet rs) throws SQLException {
        Service service = new Service();
        /*INSERT INTO service (client_id, creation_date, start_date, end_date, cancel_date, cost,"
                + " latitude_pickup, longitude_pickup, latitude_destination, longitude_destination,"
                + " street_address_pickup, city_pickup, state_pickup, zipcode_pickup,"
                + " street_address_destination, city_destination, state_destination, zipcode_destination,"
                + " client_description, tower_description)*/
        service.setId(rs.getString("id") != null ? rs.getInt("id") : null);
        service.setClientId(rs.getString("client_id") != null ? rs.getInt("client_id") : null);
        service.setStartDate(rs.getString("creation_date") != null ? Utility.StringToDate(rs.getString("creation_date")) : null);
        service.setStartDate(rs.getString("start_date") != null ? Utility.StringToDate(rs.getString("start_date")) : null);
        service.setStartDate(rs.getString("end_date") != null ? Utility.StringToDate(rs.getString("end_date")) : null);
        service.setStartDate(rs.getString("cancel_date") != null ? Utility.StringToDate(rs.getString("cancel_date")) : null);
        service.setLatitudePickup(rs.getString("latitude_pickup") != null ? rs.getDouble("latitude_pickup") : Types.DOUBLE);
        service.setLongitudePickup(rs.getString("longitude_pickup") != null ? rs.getDouble("longitude_pickup") : Types.DOUBLE);
        service.setLatitudePickup(rs.getString("latitude_destination") != null ? rs.getDouble("latitude_destination") : Types.DOUBLE);
        service.setLongitudePickup(rs.getString("longitude_destination") != null ? rs.getDouble("longitude_destination") : Types.DOUBLE);
        service.setStreetAddressPickup(rs.getString("street_address_pickup"));
        service.setCityPickup(rs.getString("city_pickup"));
        service.setStatePickup(rs.getString("state_pickup"));
        service.setZipcodePickup(rs.getString("zipcode_pickup"));
        service.setStreetAddressDestination(rs.getString("street_address_destination"));
        service.setCityDestination(rs.getString("city_destination"));
        service.setStateDestination(rs.getString("state_destination"));
        service.setZipcodeDestination(rs.getString("zipcode_destination"));
        service.setClientDescription(rs.getString("client_description"));
        service.setTowerDescription(rs.getString("tower_description"));
        return service;
    }

    private void prepareStatement(Database db) throws SQLException {
        Integer parameterIndex = 0;
        db.getPreparedStatement().setInt(++parameterIndex, this.getClientId());
        db.getPreparedStatement().setDate(++parameterIndex, this.getCreationDate()!=null?new java.sql.Date(this.getCreationDate().getTime()):null);
        db.getPreparedStatement().setDate(++parameterIndex, this.getStartDate()!=null?new java.sql.Date(this.getStartDate().getTime()):null);
        db.getPreparedStatement().setDate(++parameterIndex, this.getEndDate()!=null?new java.sql.Date(this.getEndDate().getTime()):null);
        db.getPreparedStatement().setDate(++parameterIndex, this.getCancelDate()!=null?new java.sql.Date(this.getCancelDate().getTime()):null);
        db.getPreparedStatement().setDouble(++parameterIndex, this.getCost()!=null?this.getCost():Types.DOUBLE);
        db.getPreparedStatement().setDouble(++parameterIndex, this.getLatitudePickup()!=null?this.getLatitudePickup():Types.DOUBLE);
        db.getPreparedStatement().setDouble(++parameterIndex, this.getLongitudePickup()!=null?this.getLongitudePickup():Types.DOUBLE);
        db.getPreparedStatement().setDouble(++parameterIndex, this.getLatitudeDestination()!=null?this.getLatitudeDestination():Types.DOUBLE);
        db.getPreparedStatement().setDouble(++parameterIndex, this.getLongitudeDestination()!=null?this.getLongitudeDestination():Types.DOUBLE);
        db.getPreparedStatement().setString(++parameterIndex, this.getStreetAddressPickup());
        db.getPreparedStatement().setString(++parameterIndex, this.getCityPickup());
        db.getPreparedStatement().setString(++parameterIndex, this.getStatePickup());
        db.getPreparedStatement().setString(++parameterIndex, this.getZipcodePickup());
        db.getPreparedStatement().setString(++parameterIndex, this.getStreetAddressDestination());
        db.getPreparedStatement().setString(++parameterIndex, this.getCityDestination());
        db.getPreparedStatement().setString(++parameterIndex, this.getStateDestination());
        db.getPreparedStatement().setString(++parameterIndex, this.getZipcodeDestination());
        db.getPreparedStatement().setString(++parameterIndex, this.getTowerDescription());
        db.getPreparedStatement().setString(++parameterIndex, this.getClientDescription());
    }

    /*public boolean update(List<Tower> listTower) {
        boolean resp = false;
        int parameterIndex = 0;
        int serviceId;
        HasTower rel = new HasTower();

        String sql = "INSERT INTO service (client_id, creation_date, start_date, end_date, cancel_date, cost,"
                + " latitude_pickup, longitude_pickup, latitude_destination, longitude_destination,"
                + " street_address_pickup, city_pickup, state_pickup, zipcode_pickup,"
                + " street_address_destination, city_destination, state_destination, zipcode_destination,"
                + " client_description, tower_description)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setPreparedStatement(sql);
            prepareStatement(db);
            serviceId = db.ExecuteNonQuery();
            this.setId(serviceId);

            //Create Relational Class Between Service and Tower
            for (int i = 0; i < listTower.size(); i++) {
                //rel.deleteAll();
                rel.setServiceId(serviceId);
                rel.setTowerId(listTower.get(i).getId());
                rel.create();
            }

            resp = true;
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (db != null) {
                try {
                    db.Close();
                } catch (SQLException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return resp;
    }*/
    
    
    
    public static String toJson(List<Service> list) {
        Gson gson = new GsonBuilder().setDateFormat(Utility.DATE_FORMAT_STRING_SHORT).create();
        String gsonString = gson.toJson(list, new TypeToken<List<Service>>() {
        }.getType());
        return gsonString;
    }
    
    public static List<Service> fromJson(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
        List<Service> list = gson.fromJson(json, new TypeToken<List<Service>>() {
        }.getType());
        return list;
    }

    public List<Service> selectServiceByTowerId(Integer towerId) {
        List<Service> list = new ArrayList<Service>();
        String sql;
        ResultSet rs = null;

        sql = "SELECT id, client_id, creation_date, start_date, end_date, cancel_date, cost,"
                + " latitude_pickup, longitude_pickup, latitude_destination, longitude_destination,"
                + " street_address_pickup, city_pickup, state_pickup, zipcode_pickup,"
                + " street_address_destination, city_destination, state_destination, zipcode_destination,"
                + " client_description, tower_description"
                + " FROM service s, has_tower ht"
                + " WHERE ht.service_id=s.id AND ht.tower_id="+towerId;

        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            //System.out.println("Service: "+sql);
            while (rs.next()) {
                Service service = readResulset(rs);
                list.add(service);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
            try {
                db.Close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }

        return list;
    }

    public List<Service> selectServiceByEmail(String towerEmail) {
       
		/*
		 * List<Service> list = new ArrayList<Service>();
		 * String sql; ResultSet rs = null;
		 * 
		 * sql =
		 * "SELECT id, client_id, creation_date, start_date, end_date, cancel_date, cost,"
		 * +
		 * " latitude_pickup, longitude_pickup, latitude_destination, longitude_destination,"
		 * + " street_address_pickup, city_pickup, state_pickup, zipcode_pickup," +
		 * " street_address_destination, city_destination, state_destination, zipcode_destination,"
		 * + " client_description, tower_description" +
		 * " FROM service s, has_tower ht, tower t" +
		 * " WHERE ht.service_id=s.id AND ht.tower_id=t.id AND t.email='"+towerEmail+
		 * "'";
		 * 
		 * Database db = Database.getInstance(); try { db.Connect(); db.setStatement();
		 * rs = db.ExecuteQuery(sql); //System.out.println("Service: "+sql); while
		 * (rs.next()) { Service service = readResulset(rs); list.add(service); } }
		 * catch (SQLException ex) { System.out.println(ex.toString()); } finally { if
		 * (rs != null) { try { rs.close(); } catch (SQLException ex) {
		 * System.out.println(ex.toString()); } } try { db.Close(); } catch
		 * (SQLException ex) { System.out.println(ex.toString()); } }
		 */
    	
    	 List<Service> list = new ArrayList<Service>();
    	 id = 1234;
    	 clientId = 3456;
    	 creationDate = new Date(123456);
    	 startDate = new Date(234567);
    	 endDate = new Date(345678);
    	 cancelDate = new Date(456789);
    	 cost = 5.0;
    	 latitudePickup = 65.5;
    	 longitudePickup = 66.6;
    	 latitudeDestination = 85.5;
    	 longitudeDestination = 86.6;
    	 streetAddressPickup = "CASE212A";
    	 cityPickup = "Miami";
    	 statePickup = "FL";
    	 zipcodePickup = "33199";
    	 streetAddressDestination = "PG6";
    	 cityDestination = "Miami";
    	 stateDestination = "FL";
    	 zipcodeDestination = "33199";
    	 clientDescription = "student";
    	 towerDescription = "tall";
    	 list.add(this);
    	 
        return list;
    }
    
    public List<Service> selectServiceByClientEmail(String clientEmail) {
 //       List<Service> list = new ArrayList<Service>();
//        String sql;
//        ResultSet rs = null;
//
//        sql = "SELECT s.id, s.client_id, s.creation_date, s.start_date, s.end_date, s.cancel_date, s.cost,"
//                + " s.latitude_pickup, s.longitude_pickup, s.latitude_destination, s.longitude_destination,"
//                + " s.street_address_pickup, s.city_pickup, s.state_pickup, s.zipcode_pickup,"
//                + " s.street_address_destination, s.city_destination, s.state_destination, s.zipcode_destination,"
//                + " s.client_description, s.tower_description"
//                + " FROM service s, client c"
//                + " WHERE c.id=s.client_id AND c.email='"+clientEmail+"'";
//
//        Database db = Database.getInstance();
//        try {
//            db.Connect();
//            db.setStatement();
//            rs = db.ExecuteQuery(sql);
//            //System.out.println("Service: "+sql);
//            while (rs.next()) {
//                Service service = readResulset(rs);
//                list.add(service);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.toString());
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException ex) {
//                    System.out.println(ex.toString());
//                }
//            }
//            try {
//                db.Close();
//            } catch (SQLException ex) {
//                System.out.println(ex.toString());
//            }
//        }

     List<Service> list = new ArrayList<Service>();
   	 id = 1234;
   	 clientId = 3456;
   	 creationDate = new Date(123456);
   	 startDate = new Date(234567);
   	 endDate = new Date(345678);
   	 cancelDate = new Date(456789);
   	 cost = 5.0;
   	 latitudePickup = 65.5;
   	 longitudePickup = 66.6;
   	 latitudeDestination = 85.5;
   	 longitudeDestination = 86.6;
   	 streetAddressPickup = "CASE212A";
   	 cityPickup = "Miami";
   	 statePickup = "FL";
   	 zipcodePickup = "33199";
   	 streetAddressDestination = "PG6";
   	 cityDestination = "Miami";
   	 stateDestination = "FL";
   	 zipcodeDestination = "33199";
   	 clientDescription = "student";
   	 towerDescription = "tall";
   	 list.add(this);
   	 
       return list;
    }

    boolean updatePickup(String address, Integer serviceId) {
//       boolean resp = false;
//        int parameterIndex = 0;
//
//        String sql = "UPDATE service SET street_address_pickup=? WHERE id=?";
//
//        Database db = Database.getInstance();
//        try {
//            db.Connect();
//            db.setPreparedStatement(sql);
//            db.getPreparedStatement().setString(++parameterIndex, this.getStreetAddressPickup());
//            db.getPreparedStatement().setInt(++parameterIndex, this.getId());
//            db.ExecuteNonQuery();
//            resp = true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            if (db != null) {
//                try {
//                    db.Close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }

        boolean resp = true;
        return resp;
    }

    boolean updateDestination(String address, Integer serviceId) {
        boolean resp = false;
        int parameterIndex = 0;
        

        String sql = "UPDATE service SET street_address_destination=? WHERE id=?";

        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setPreparedStatement(sql);
            db.getPreparedStatement().setString(++parameterIndex, address);
            db.getPreparedStatement().setInt(++parameterIndex, this.getId());
            db.ExecuteNonQuery();
            resp = true;
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (db != null) {
                try {
                    db.Close();
                } catch (SQLException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return resp;
    }

}
