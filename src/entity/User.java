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
public class User {

    private String email;
    private String password;
    private Integer userTypeId;
    private String fname;
    private String lname;
    private String phone;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private Date dob;
    private String blocked;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //TRUE if email and password matches one registry in DB
    public boolean validateUser(String email, String pass) {
        boolean respuesta = false;
        String sql;
        ResultSet rs = null;

        sql = "SELECT email, user_type_id, fname, lname, phone, street_address, city, state, zipcode, dob, blocked FROM user WHERE email='" + email + "' AND password='" + pass + "'  AND (blocked!='' OR blocked is null)";

        Database db = Database.getInstance();
        //Database db = new Database();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            if (rs != null && rs.next()) {
                this.setEmail(rs.getString("email"));
                this.setUserTypeId(rs.getInt("user_type_id"));
                this.setFname(rs.getString("fname"));
                this.setLname(rs.getString("lname"));
                this.setPhone(rs.getString("phone"));
                this.setStreetAddress(rs.getString("street_address"));
                this.setStreetAddress(rs.getString("city"));
                this.setStreetAddress(rs.getString("state"));
                this.setStreetAddress(rs.getString("zipcode"));
                this.setStreetAddress(rs.getString("street_address"));
                this.setDob(rs.getString("dob") != null ? rs.getDate("dob") : null);
                respuesta = true;
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
            if (db != null) {
                try {
                    db.Close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
        }

        return respuesta;
    }

    //TRUE if User was created successfully
    public boolean createUser() {

        boolean resp = false;
        //int parameterIndex = 0;

        String sql = "INSERT INTO user (email, password, user_type_id, phone, fname, lname, street_address, city, state, zipcode, dob)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        Database db = Database.getInstance();
        //Database db = new Database();
        try {
            db.Connect();
            db.setPreparedStatement(sql);
            addValues(db);
            db.ExecuteNonQuery();
            resp = true;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (db != null) {
                try {
                    db.Close();
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return resp;
    }

    //Returns all existing users in DB
    public List<User> SelectAll() {
        List<User> list = new ArrayList<User>();
        String sql;
        ResultSet rs = null;

        sql = "SELECT email, password, user_type_id, fname, lname, phone, street_address, city, state, zipcode, dob, blocked FROM user";

        Database db = new Database();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            while (rs.next()) {
                User obj = readResult(rs);
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

    //add all values present in User Object
    private void addValues(Database db) throws SQLException {
        Integer parameterIndex = 0;
        //email, password, user_type_id, phone, fname, lname, street_address, city, state, zipcode, dob
        db.getPreparedStatement().setString(++parameterIndex, this.getEmail().trim());
        db.getPreparedStatement().setString(++parameterIndex, this.getPassword());
        db.getPreparedStatement().setInt(++parameterIndex, this.getUserTypeId());
        db.getPreparedStatement().setString(++parameterIndex, this.getPhone());
        db.getPreparedStatement().setString(++parameterIndex, this.getFname());
        db.getPreparedStatement().setString(++parameterIndex, this.getLname());
        db.getPreparedStatement().setString(++parameterIndex, this.getStreetAddress());
        db.getPreparedStatement().setString(++parameterIndex, this.getCity());
        db.getPreparedStatement().setString(++parameterIndex, this.getState());
        db.getPreparedStatement().setString(++parameterIndex, this.getZipcode());
        db.getPreparedStatement().setDate(++parameterIndex, this.getDob() != null ? new java.sql.Date(this.getDob().getTime()) : null);
    }

    //Returns User with information in ResultSet
    private User readResult(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setEmail(rs.getString("email"));
        obj.setPassword(rs.getString("password"));
        obj.setUserTypeId(rs.getInt("user_type_id"));
        obj.setFname(rs.getString("fname"));
        obj.setLname(rs.getString("lname"));
        obj.setPhone(rs.getString("phone"));
        obj.setStreetAddress(rs.getString("street_address"));
        obj.setCity(rs.getString("city"));
        obj.setState(rs.getString("state"));
        obj.setZipcode(rs.getString("zipcode"));
        obj.setDob(rs.getString("dob") != null ? rs.getDate("dob") : null);
        obj.setBlocked(rs.getString("blocked"));
        return obj;
    }

    public static List<User> fromJsonUser(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new Utility.JsonDateDeserializer()).create();
        List<User> list = gson.fromJson(json, new com.google.gson.reflect.TypeToken<List<User>>() {
        }.getType());
        return list;
    }

    //Select a list of users that match email
    public List<User> selectByEmail(String email) {
        List<User> list = new ArrayList<User>();
        String sql;
        ResultSet rs = null;

        sql = "SELECT email, password, user_type_id, fname, lname, phone, street_address, city, state, zipcode, dob, blocked"
                + " FROM user"
                + " WHERE email='" + email + "'";

        Database db = new Database();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            while (rs.next()) {
                User obj = readResult(rs);
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

    //Blocks user
    public boolean block() {
        boolean resp = false;
        int parameterIndex = 0;

        String sql = "UPDATE user SET blocked=? WHERE email=?";

        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setPreparedStatement(sql);
            db.getPreparedStatement().setString(++parameterIndex, this.getBlocked());
            db.getPreparedStatement().setString(++parameterIndex, this.getEmail());
            db.ExecuteNonQuery();
            resp = true;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (db != null) {
                try {
                    db.Close();
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return resp;
    }

    //Blocks updates User information
    public boolean update() {
        boolean resp = false;
        int parameterIndex = 0;

        String sql = "UPDATE user SET fname=?, lname=?, phone=?, street_address=?, city=?, state=?, zipcode=?, dob=? WHERE email=?";

        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setPreparedStatement(sql);
            db.getPreparedStatement().setString(++parameterIndex, this.getFname());
            db.getPreparedStatement().setString(++parameterIndex, this.getLname());
            db.getPreparedStatement().setString(++parameterIndex, this.getPhone());
            db.getPreparedStatement().setString(++parameterIndex, this.getStreetAddress());
            db.getPreparedStatement().setString(++parameterIndex, this.getCity());
            db.getPreparedStatement().setString(++parameterIndex, this.getState());
            db.getPreparedStatement().setString(++parameterIndex, this.getCity());
            db.getPreparedStatement().setString(++parameterIndex, this.getState());
            db.getPreparedStatement().setString(++parameterIndex, this.getZipcode());
            db.getPreparedStatement().setDate(++parameterIndex, this.getDob() != null ? new java.sql.Date(this.getDob().getTime()) : null);
            db.getPreparedStatement().setString(++parameterIndex, this.getEmail());
            db.ExecuteNonQuery();
            resp = true;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (db != null) {
                try {
                    db.Close();
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return resp;
    }

    /*public Integer selectIdByEmail(String email) {
        return null;
    }*/
}
