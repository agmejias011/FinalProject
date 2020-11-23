/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Utility;

/**
 *
 * @author Juan
 */
public class Client extends User {

    private Integer id;

    public Client() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean create() {

        boolean resp = false;
        int parameterIndex = 0;

        String sql = "INSERT INTO client (email) VALUES (?)";

        this.setUserTypeId(1);

        if (createUser()) {

            Database db = Database.getInstance();
            try {
                db.Connect();
                db.setPreparedStatement(sql);
                db.getPreparedStatement().setString(++parameterIndex, this.getEmail());
                db.ExecuteNonQuery();
                resp = true;
            } catch (SQLException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (db != null) {
                    try {
                        db.Close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return resp;
    }

    public List<Client> selectAll() {
        List<Client> list = new ArrayList<Client>();
        String sql;
        ResultSet rs = null;

        sql = "SELECT c.id, c.email, u.user_type_id, u.fname, u.lname, u.street_address, u.city, u.state, u.zipcode, u.dob, u.blocked FROM user u, client c"
                + " WHERE c.email=u.email";

        //Database db = new Database();
        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            while (rs.next()) {
                Client obj = readResult(rs);
                list.add(obj);
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

    public void selectIdByEmail() {
        String sql;
        ResultSet rs = null;

        sql = "SELECT id FROM client WHERE email='" + this.getEmail() + "'";

        //Database db = new Database();
        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            if (rs.next()) {
                this.setId(rs.getString("id") != null ? rs.getInt("id") : null);
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

    }

    public Client selectById(Integer clientId) {
        //List<Client> list = new ArrayList<Client>();
        String sql;
        ResultSet rs = null;
        Client obj = null;

        sql = "SELECT c.id, c.email, u.user_type_id, u.fname, u.lname, u.street_address, u.city, u.state, u.zipcode, u.dob, u.blocked FROM user u, client c"
                + " WHERE c.email=u.email AND c.id=" + clientId;

        //Database db = new Database();
        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            if (rs.next()) {
                obj = readResult(rs);
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

        return obj;
    }

    private Client readResult(ResultSet rs) throws SQLException {
        Client obj = new Client();
        obj.setId(rs.getString("id") != null ? rs.getInt("id") : null);
        obj.setEmail(rs.getString("email"));
        obj.setFname(rs.getString("fname"));
        obj.setLname(rs.getString("lname"));
        obj.setStreetAddress(rs.getString("street_address"));
        obj.setCity(rs.getString("city"));
        obj.setState(rs.getString("state"));
        obj.setZipcode(rs.getString("zipcode"));
        obj.setDob(rs.getString("dob") != null ? rs.getDate("dob") : null);
        obj.setBlocked(rs.getString("blocked"));
        return obj;
    }

    public static String toJson(List<Client> list) {
        Gson gson = new GsonBuilder().setDateFormat(Utility.DATE_FORMAT_STRING_SHORT).create();
        String gsonString = gson.toJson(list, new com.google.gson.reflect.TypeToken<List<Client>>() {
        }.getType());
        return gsonString;
    }

    public static List<Client> fromJson(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new Utility.JsonDateDeserializer()).create();
        List<Client> list = gson.fromJson(json, new com.google.gson.reflect.TypeToken<List<Client>>() {
        }.getType());
        return list;
    }

    public List<Client> selectClientByEmail(String email) {
        List<Client> list = new ArrayList<Client>();
        String sql;
        ResultSet rs = null;
        Client obj = null;

        sql = "SELECT c.id, c.email, u.user_type_id, u.fname, u.lname, u.street_address, u.city, u.state, u.zipcode, u.dob, u.blocked FROM user u, client c"
                + " WHERE c.email=u.email AND u.email='" + email + "'";

        //Database db = new Database();
        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            while (rs.next()) {
                obj = readResult(rs);
                list.add(obj);
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
    
    //Inherits 
    public boolean updateClient() {
        return update();
    }

}
