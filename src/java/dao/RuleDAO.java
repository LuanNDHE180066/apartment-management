/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import jdbc.DBContext;
import model.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Rule;
import util.Util;

/**
 *
 * @author quang
 */
public class RuleDAO extends DBContext {

    public List<Rule> getAllRule() {
        List<Rule> listRule = new ArrayList<>();
        StaffDAO daoS = new StaffDAO();
        String sql = "select * from [Rule] order by id desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listRule.add(new Rule(rs.getString("id"), rs.getString("title"),
                        rs.getString("description"), rs.getDate("date").toString(),
                        rs.getDate("effectivedate").toString(), rs.getString("status"), daoS.getById(rs.getString("sid"))));
            }
            return listRule;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public boolean insertRule(Rule rule) {
        String sql = "insert into [Rule] (id, title,description,date, effectivedate,status,sid) values(?,?,?,?,?,?,?)";
        Util u = new Util();
        RuleDAO daoR = new RuleDAO();
        List<Rule> listRule = daoR.getAllRule();
        int newID_raw = 0;
        if (listRule.size() != 0) {
            newID_raw = Integer.parseInt(listRule.get(0).getId());
        }
        String newID = "";
        newID += (newID_raw + 1);
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newID);
            ps.setString(2, rule.getTitle());
            ps.setString(3, rule.getDescription());
            ps.setString(4, rule.getDate());
            ps.setString(5, rule.getEffectiveDate());
            ps.setString(6, rule.getStatus().equalsIgnoreCase("1") ? "Active" : "Inactive");
            ps.setString(7, rule.getStaff().getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RuleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateRule(Rule rule) {
        try {
            String sql = "UPDATE [Rule] SET title = ?, description = ?, status = ?, effectivedate = ? WHERE id = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, rule.getTitle());
            pre.setString(2, rule.getDescription());
            pre.setString(3, rule.getStatus());
            pre.setString(4, rule.getEffectiveDate());
            pre.setString(5, rule.getId());

            int rowsUpdated = pre.executeUpdate();

            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void deleteRule(String id) {
        try {
            String sql = "Delete from [Rule] where id=?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Rule getById(String id) {
        List<Rule> all = this.getAllRule();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId().equals(id)) {
                return all.get(i);
            }
        }
        return null;
    }

    public List<Rule> filterRule(String title, String date) {
        String sql = "select * from [Rule]  where 1=1";
        if (title != null && !title.isEmpty()) {
            sql += " and title like '%" + title + "%'";

        }
        if (date != null && !date.isEmpty()) {
            sql += " and DATEDIFF(DAY, effectivedate, GETDATE()) BETWEEN 0 AND " + date;
        }
        sql += " order by id desc";
        List<Rule> list = new ArrayList<>();
        StaffDAO daoS = new StaffDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Rule(rs.getString("id"), rs.getString("title"),
                        rs.getString("description"), rs.getDate("date").toString(),
                        rs.getDate("effectivedate").toString(), rs.getString("status"), daoS.getById(rs.getString("sid"))));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean changeStatus() {
        String sql = "UPDATE [Rule] \n"
                + "SET status = 'Active' \n"
                + "WHERE effectiveDate <= CAST(GETDATE() AS DATE) \n"
                + "AND status != 'Active';";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public static void main(String[] args) {
        RuleDAO dao = new RuleDAO();
        System.out.println(dao.filterRule(null, "1"));
     
    }
}
