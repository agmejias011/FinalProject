/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class HasTower {

    private Integer serviceId;
    private Integer towerId;
    private Date clientApprovedDate;
    private Date towerAcceptDate;
    private Date towerDeclineDate;
    private Integer clientRating;
    private String clientComment;
    private Integer towerRating;
    private String towerComment;

    public HasTower() {
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getTowerId() {
        return towerId;
    }

    public void setTowerId(Integer towerId) {
        this.towerId = towerId;
    }

    public Integer getClientRating() {
        return clientRating;
    }

    public void setClientRating(Integer clientRating) {
        this.clientRating = clientRating;
    }

    public String getClientComment() {
        return clientComment;
    }

    public void setClientComment(String clientComment) {
        this.clientComment = clientComment;
    }

    public Integer getTowerRating() {
        return towerRating;
    }

    public void setTowerRating(Integer towerRating) {
        this.towerRating = towerRating;
    }

    public String getTowerComment() {
        return towerComment;
    }

    public void setTowerComment(String towerComment) {
        this.towerComment = towerComment;
    }

    public Date getClientApprovedDate() {
        return clientApprovedDate;
    }

    public void setClientApprovedDate(Date clientApprovedDate) {
        this.clientApprovedDate = clientApprovedDate;
    }

    public Date getTowerAcceptDate() {
        return towerAcceptDate;
    }

    public void setTowerAcceptDate(Date towerAcceptDate) {
        this.towerAcceptDate = towerAcceptDate;
    }

    public Date getTowerDeclineDate() {
        return towerDeclineDate;
    }

    public void setTowerDeclineDate(Date towerDeclineDate) {
        this.towerDeclineDate = towerDeclineDate;
    }

    public boolean create() {
        boolean resp = false;

        int parameterIndex = 0;

        String sql = "INSERT INTO has_tower (service_id, tower_id, client_approved_date)"
                + " VALUES (?,?,?)";

        Database db = Database.getInstance();
        //Database db = new Database();
        try {
            db.Connect();
            db.setPreparedStatement(sql);
            db.getPreparedStatement().setInt(++parameterIndex, this.getServiceId());
            db.getPreparedStatement().setInt(++parameterIndex, this.getTowerId());
            db.getPreparedStatement().setDate(++parameterIndex, this.getClientApprovedDate()!=null?new java.sql.Date(this.getClientApprovedDate().getTime()):null);
            db.ExecuteNonQuery();
            resp = true;
        } catch (SQLException ex) {
            Logger.getLogger(HasTower.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (db != null) {
                try {
                    db.Close();
                } catch (SQLException ex) {
                    Logger.getLogger(HasTower.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return resp;
    }

    public boolean acceptRequest() {
        boolean resp = false;
        this.setTowerAcceptDate(new Date());

        boolean validAcceptance = validateAcceptance();
        
        if (validAcceptance) {
            String sql = "UPDATE has_tower SET tower_accept_date=? WHERE service_id=? AND tower_id=?";

            Database db = Database.getInstance();
            //Database db = new Database();
            try {
                db.Connect();
                db.setPreparedStatement(sql);
                //for (int i = 0; i < this.getTowerId().size(); i++) {
                int parameterIndex = 0;
                db.getPreparedStatement().setDate(++parameterIndex, this.getTowerAcceptDate()!=null? new java.sql.Date(this.getTowerAcceptDate().getTime()):null);
                db.getPreparedStatement().setInt(++parameterIndex, this.getServiceId());
                db.getPreparedStatement().setInt(++parameterIndex, this.getTowerId());
                db.ExecuteNonQuery();
                //}
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
        }
        return resp;
    }

    public boolean cancelRequest() {
		/*
		 * boolean resp = false; this.setTowerDeclineDate(new Date());
		 * 
		 * String sql =
		 * "UPDATE has_tower SET tower_decline_date=? WHERE service_id=? AND tower_id=?"
		 * ;
		 * 
		 * Database db = Database.getInstance(); try { db.Connect();
		 * db.setPreparedStatement(sql); //for (int i = 0; i < this.getTowerId().size();
		 * i++) { int parameterIndex = 0;
		 * db.getPreparedStatement().setDate(++parameterIndex,
		 * this.getTowerDeclineDate()!=null? new
		 * java.sql.Date(this.getTowerDeclineDate().getTime()):null);
		 * db.getPreparedStatement().setInt(++parameterIndex, this.getServiceId());
		 * db.getPreparedStatement().setInt(++parameterIndex, this.getTowerId());
		 * db.ExecuteNonQuery(); //} resp = true; } catch (SQLException ex) {
		 * Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex); }
		 * finally { if (db != null) { try { db.Close(); } catch (SQLException ex) {
		 * Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex); } } }
		 */

        return true;
    }

    private boolean validateAcceptance() {
        boolean resp = false;

        List<HasTower> list = new ArrayList<HasTower>();
        String sql;
        ResultSet rs = null;
        Integer count = 0;

        sql = "SELECT service_id, tower_id, tower_accept_date, tower_decline_date"
                + " FROM has_tower WHERE service_id=" + this.getServiceId();

        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            while (rs.next()) {
                HasTower obj = new HasTower();
                obj.setServiceId(rs.getString("service_id") != null ? rs.getInt("service_id") : null);
                obj.setTowerId(rs.getString("tower_id") != null ? rs.getInt("tower_id") : null);
                obj.setTowerAcceptDate(rs.getString("tower_accept_date") != null ? rs.getDate("tower_accept_date") : null);
                obj.setTowerDeclineDate(rs.getString("tower_decline_date") != null ? rs.getDate("tower_decline_date") : null);
                list.add(obj);
            }
            resp = checkHasTower(list, towerId);
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

        return resp;
    }

    private boolean checkHasTower(List<HasTower> list, Integer towerId) {
        boolean resp = true;

        for (int i=0; i<list.size(); i++) {
            if(list.get(i).getTowerAcceptDate()!=null && list.get(i).getTowerDeclineDate()==null){
                resp = false;
                break;
            }
        }
        
        return resp;
    }

    public List<HasTower> selectAll() {
        List<HasTower> list = new ArrayList<HasTower>();
        String sql;
        ResultSet rs = null;
        Integer count = 0;

        sql = "SELECT service_id, tower_id, tower_accept_date, tower_decline_date"
                + " FROM has_tower WHERE service_id=" + this.getServiceId();

        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            while (rs.next()) {
                HasTower obj = new HasTower();
                obj.setServiceId(rs.getString("service_id") != null ? rs.getInt("service_id") : null);
                obj.setTowerId(rs.getString("tower_id") != null ? rs.getInt("tower_id") : null);
                obj.setTowerAcceptDate(rs.getString("tower_accept_date") != null ? rs.getDate("tower_accept_date") : null);
                obj.setTowerDeclineDate(rs.getString("tower_decline_date") != null ? rs.getDate("tower_decline_date") : null);
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

    boolean rateTower(Integer towerId, Integer serviceId, Integer rating) {
        boolean resp = false;
        this.setTowerDeclineDate(new Date());

        String sql = "UPDATE has_tower SET tower_rating=? WHERE service_id=? AND tower_id=?";

        Database db = Database.getInstance();
        try {
            db.Connect();
            db.setPreparedStatement(sql);
            //for (int i = 0; i < this.getTowerId().size(); i++) {
            int parameterIndex = 0;
            db.getPreparedStatement().setInt(++parameterIndex, rating);
            db.getPreparedStatement().setInt(++parameterIndex, this.getServiceId());
            db.getPreparedStatement().setInt(++parameterIndex, this.getTowerId());
            db.ExecuteNonQuery();
            //}
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
