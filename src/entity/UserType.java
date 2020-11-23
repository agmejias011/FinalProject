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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class UserType {

    private Integer id;
    private String name;

    public UserType() {
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

    public boolean create() {

        boolean resp = false;
        int parameterIndex = 0;

        String sql = "INSERT INTO user_type (name) VALUES (?)";

        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setPreparedStatement(sql);
            db.getPreparedStatement().setString(++parameterIndex, this.getName());
            db.ExecuteNonQuery();
            resp = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserType.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (db != null) {
                try {
                    db.Close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserType.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return resp;
    }

    public List<UserType> selectAll() {
        List<UserType> list = new ArrayList<UserType>();
        String sql;
        ResultSet rs = null;

        sql = "SELECT id, name FROM user_type";

        //Database db = new Database();
        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            while (rs.next()) {
                UserType userType = new UserType();
                userType.setName(rs.getString("name"));
                userType.setId(rs.getString("id") != null ? rs.getInt("id") : null);
                list.add(userType);
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

}
