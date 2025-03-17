/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.response.EmailInvoice;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBContext;
import model.Account;
import util.Util;

/**
 *
 * @author thanh
 */
public class AccountDAO extends DBContext {

    public String getcheckTable(int roleId) {
        String table = "Empty";
        if(roleId == 1){
            table = "Resident";
        }else{
            table = "Staff";
        }
        return table;
    }

    public Account getAccountByUsernameandRole(String user, int roleId) {
        String sql = null;
        Account s = null;
        AccountDAO dao = new AccountDAO();
        String table = null;
        table = dao.getcheckTable(roleId);
        if (table.equals("Empty")) {
            return s;
//        } else if (table.equals("Resident")) {
//            sql = "SELECT * FROM Resident WHERE [username]=?";
//        } else if (table.equals("Staff")) {
//            sql = "SELECT * FROM Staff WHERE [username]=?";
//        }
        }
        else{
            sql = "SELECT * FROM "+table+" WHERE [username]=? and roleid = ?";
        }
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, user);
            pre.setInt(2, roleId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                s = new Account(rs.getString("username"), rs.getString("password"), rs.getString("Email"), rs.getString("Id"), rs.getInt("roleId"));
                if (table.equals("Resident")) {
                    s.setActive(rs.getInt("active"));
                }else{
                    s.setActive(rs.getInt("status"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public void changePassword(String username, String password, int roleId) {
        String sql = "Update ";
        if (roleId == 1) {
            sql += "Resident set password = ? where username = ? ";
        } else {
            sql += "Staff set password = ? where username = ? ";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Util.encryptPassword(password));
            ps.setString(2, username);
            ps.executeQuery();
        } catch (SQLException ex) {

        }

    }

    public Account getByEmail(String email) {
        Util util = new Util();
        List<Account> list = this.getAllAccount();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEmail().equals(email)) {
                return list.get(i);
            }
        }
        return null;
    }

    public List<Account> getAllAccount() {
        ResidentDAO daoR = new ResidentDAO();
        StaffDAO daoS = new StaffDAO();

        List<Account> list = new ArrayList<>();
        list.addAll(daoR.getAllResidentAccount());
        list.addAll(daoS.getAllStaffAccount());

        return list;
    }

    public Account getAccountById(String pId) {
        List<Account> list = this.getAllAccount();
        for (Account a : list) {
            if (a.getpId().equals(pId)) {
                return a;
            }
        }
        return null;
    }

    public Account getAccountByUsername(String username) {
        List<Account> list = this.getAllAccount();
        for (Account a : list) {
            if (a.getUsername().equals(username)) {
                return a;
            }
        }
        return null;
    }

    public String getIdByUsernameAndRole(String username, String role) {
        String sql = "select id from ";
        if (role.equalsIgnoreCase("1")) {
            sql += "resident where username = '" + username + "'";
        } else {
            sql += "staff where username = '" + username + "'";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void changeImageByAccount(Account acc,String image){
        String sql = "update ";
        if(acc.getRoleId() == 1){
            sql +="resident ";
        }else{
            sql +="staff ";
        }
        sql += "set image=? where username=? and roleid=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,image);
            ps.setString(2,acc.getUsername());
            ps.setInt(3,acc.getRoleId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<String> getNotificationsByRoleAndPid(int role,String pid){
        String sql ="select id ";
        List<String> list = new ArrayList<>();
        switch(role){
            case 2:
                sql += "from request where Status = 0";
                break;
            case 4:
                sql += "from request where sid = '"+pid+"'";
                break;
            case 5:
                sql += "from request where sid = '"+pid+"'";
                break;
            default:
                return list;
        }
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        //System.out.println(""+dao.getAccountByUsernameandRole("userC", 0));
        List<String> list = dao.getNotificationsByRoleAndPid(2, "S1015");
        for (String string : list) {
            System.out.println(""+string);
        }
    }

}
