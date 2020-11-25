/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class Application {

    private Integer id;
    private String name;
    private String url;

    public Application() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Application> selectByUserTypeId(Integer userTypeId) {
        // List<Application> list = new ArrayList<Application>();
        // String sql;
        // ResultSet rs = null;
        // Application obj;
        //
        // sql = "SELECT a.id, a.name, a.url"
        // + " FROM application a, has_permission hp"
        // + " WHERE hp.application_id=a.id AND hp.user_type_id=" + userTypeId;
        //
        // //Database db = new Database();
        // Database db = Database.getInstance();
        // try {
        // db.Connect();
        // db.setStatement();
        // rs = db.ExecuteQuery(sql);
        // while (rs.next()) {
        // obj = new Application();
        // obj.setId(rs.getString("id") != null ? rs.getInt("id") : null);
        // obj.setName(rs.getString("name"));
        // obj.setUrl(rs.getString("url"));
        // list.add(obj);
        // }
        // } catch (SQLException ex) {
        // System.out.println(ex.toString());
        // } finally {
        // if (rs != null) {
        // try {
        // rs.close();
        // } catch (SQLException ex) {
        // System.out.println(ex.toString());
        // }
        // }
        // try {
        // db.Close();
        // } catch (SQLException ex) {
        // System.out.println(ex.toString());
        // }
        // }

        List<Application> list = new ArrayList<Application>();
        this.id = 25;
        this.name = "ABC";
        this.url = "www.url.com";

        list.add(this);

        return list;
    }

}
