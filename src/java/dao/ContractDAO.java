/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import jdbc.DBContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBContext;
import model.Apartment;
import model.Company;
import model.Contract;
import model.Floor;
import model.RoomType;
import model.Staff;
import util.Util;

/**
 *
 * @author quang
 */
public class ContractDAO extends DBContext {
//Contract(String id, Staff staff, Staff admin, Staff accountant, Company company, String endDate, String startDate, String paymentTems,
    //String signDate, String title, String description, String image, int status)

    public List<Contract> getAll() {
        String sql = "select * from Contract";
        CompanyDAO daoCP = new CompanyDAO();
        StaffDAO daoSt = new StaffDAO();
        List<Contract> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Contract(rs.getString("id"),
                        daoSt.getById(rs.getString("sId")),
                        daoSt.getById(rs.getString("adminId")),
                        daoSt.getById(rs.getString("accountantId")),
                        daoCP.getById(rs.getString("cId")),
                        rs.getString("enddate"),
                        rs.getString("Startdate"),
                        rs.getString("paymenttems"),
                        rs.getString("signdate"),
                        rs.getString("title"),
                        rs.getString("Description"),
                        rs.getString("image"),
                        rs.getInt("status")));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ContractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Contract> filterContract(String title, String startDate, String endDate) {
        String sql = "select * from Contract where 1 = 1 ";
        CompanyDAO daoCP = new CompanyDAO();
        StaffDAO daoSt = new StaffDAO();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (title != "") {
            sql += "and title = '%" + title + "%'";
        }
        if (startDate != "") {
            Date date = Date.valueOf(startDate);
            String formatDate = format.format(date);
            sql += " and startdate >= '" + formatDate + "'";
        }

        if (endDate != "") {
            Date date = Date.valueOf(endDate);
            String formatDate = format.format(date);
            sql += " and startdate <= '" + formatDate + "'";
        }
        List<Contract> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new Contract(rs.getString("id"),
                        daoSt.getById(rs.getString("sId")),
                        daoSt.getById(rs.getString("adminId")),
                        daoSt.getById(rs.getString("accountantId")),
                        daoCP.getById(rs.getString("cId")),
                        rs.getString("enddate"),
                        rs.getString("Startdate"),
                        rs.getString("paymenttems"),
                        rs.getString("signdate"),
                        rs.getString("title"),
                        rs.getString("Description"),
                        rs.getString("image"),
                        rs.getInt("status")));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ContractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Contract getById(String id) {
        String sql = "select * from Contract where id  = " + id;
        CompanyDAO daoCP = new CompanyDAO();
        StaffDAO daoSt = new StaffDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Contract(rs.getString("id"),
                        daoSt.getById(rs.getString("sId")),
                        daoSt.getById(rs.getString("adminId")),
                        daoSt.getById(rs.getString("accountantId")),
                        daoCP.getById(rs.getString("cId")),
                        rs.getString("enddate"),
                        rs.getString("Startdate"),
                        rs.getString("paymenttems"),
                        rs.getString("signdate"),
                        rs.getString("title"),
                        rs.getString("Description"),
                        rs.getString("image"),
                        rs.getInt("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean addContract(Contract c) {
        String sql = "insert into Contract (sId,cId,Startdate,Enddate,paymenttems,signdate,title,Description,status,id,accountantId,adminId,image) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Util u = new Util();
        ContractDAO ctd = new ContractDAO();
        List<Contract> listCont = ctd.getAll();
        int lastNum = 0;
        if (listCont.size() != 0) {
            lastNum = u.getNumberFromTextOnlyNumber(listCont.get(listCont.size() - 1).getId());
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getStaff().getId());
            ps.setString(2, c.getCompany().getId());
            ps.setString(3, c.getStartDate());
            ps.setString(4, c.getEndDate());
            ps.setString(5, c.getPaymentTems());
            ps.setString(6, c.getSignDate());
            ps.setString(7, c.getTitle());
            ps.setString(8, c.getDescription());
            ps.setInt(9, 1);
            ps.setString(10, (lastNum + 1) + "");
            ps.setString(11, c.getAccountant().getId());
            ps.setString(12, c.getAdmin().getId());
            ps.setString(13, c.getImage());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public static void main(String[] args) {
        ContractDAO dap = new ContractDAO();
        StaffDAO std = new StaffDAO();
        CompanyDAO cpd = new CompanyDAO();
        Contract c = new Contract(std.getById("S1003"), cpd.getById("C001"), "2025-02-12", "2025-02-12", "2025-02-12", "2025-02-12", "hehe", "hehe", std.getById("S1004"), std.getById("S1003"), "images/avatar/anh.jpg");
        //System.out.println(dap.filterContract("", "", "2025-3-27").size());
        System.out.println(dap.getAll().get(0).getCompany());
    }
}
