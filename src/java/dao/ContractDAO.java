/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.beans.Statement;
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
        String sql = "  select * from Contract order by id asc";
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

    public List<Contract> getAllactive() {
        String sql = "  select * from Contract where status=1 order by id asc";
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
        String sql = "SELECT * FROM Contract WHERE status = 1"; // Chỉ lấy hợp đồng đã duyệt
        List<Object> params = new ArrayList<>();

        if (title != null && !title.trim().isEmpty()) {
            sql += " AND title LIKE ?";
            params.add("%" + title + "%");
        }
        if (startDate != null && !startDate.trim().isEmpty()) {
            sql += " AND startdate >= ?";
            params.add(Date.valueOf(startDate));
        }
        if (endDate != null && !endDate.trim().isEmpty()) {
            sql += " AND startdate <= ?";
            params.add(Date.valueOf(endDate));
        }
        sql+=" ORDER BY TRY_CAST(id AS INT) DESC";

        List<Contract> list = new ArrayList<>();
        CompanyDAO daoCP = new CompanyDAO();
        StaffDAO daoSt = new StaffDAO();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Contract(
                        rs.getString("id"),
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
                        rs.getInt("status")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<String> getallimgbyID(String id){
        String sql="  select * from [ContractImages] where [contractId]= "+id;
        List<String> list=new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(rs.getString("imageUrl"));
            }
        } catch (Exception e) {
        }
        return list;
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

    public Contract getLastInsertedContract() {
        String sql = "SELECT top 1 * FROM Contract ORDER BY TRY_CAST(id AS INT) DESC;";
        CompanyDAO daoCP = new CompanyDAO();
        StaffDAO daoSt = new StaffDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
            ex.printStackTrace();
        }
        return null;
    }

    public boolean addContract(Contract c, List<String> images) {
    String sqlContract = "INSERT INTO Contract (sId, cId, Startdate, Enddate, paymenttems, signdate, title, Description, status, id, accountantId, adminId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String sqlImage="INSERT INTO ContractImages(contractId, imageUrl) values (?,?)";
    ContractDAO ctd = new ContractDAO();
        List<Contract> listCont = ctd.getAll();
        int lastNum = 0;
        if (listCont.size() != 0) {
            lastNum = listCont.size()+1;}
    try {

        PreparedStatement ps = connection.prepareStatement(sqlContract);
        ps.setString(1, c.getStaff().getId());
        ps.setString(2, c.getCompany().getId());
        ps.setString(3, c.getStartDate());
        ps.setString(4, c.getEndDate());
        ps.setString(5, c.getPaymentTems());
        ps.setString(6, c.getSignDate());
        ps.setString(7, c.getTitle());
        ps.setString(8, c.getDescription());
        ps.setInt(9, 0); // status mặc định là 0
        ps.setString(10, (lastNum + 1) + "");
        ps.setString(11, c.getAccountant().getId());
        ps.setString(12, c.getAdmin().getId());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            return false;
        }

        

        if ( !images.isEmpty()) {
            PreparedStatement psImage = connection.prepareStatement(sqlImage);
            for (String imagePath : images) {
                psImage.setString(1, (lastNum + 1) + "");
                psImage.setString(2, imagePath);
                psImage.executeUpdate();
            }
        }

        return true;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


    public boolean updateStatus(String id) {
    String sql = "UPDATE Contract SET status = ? WHERE id = ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 1);
        ps.setString(2, id);
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


    public static void main(String[] args) {
        ContractDAO dap = new ContractDAO();
        StaffDAO std = new StaffDAO();
        CompanyDAO cpd = new CompanyDAO();
//        Contract c = new Contract(std.getById("S1003"), cpd.getById("C001"), "2025-02-12", "2025-02-12", "2025-02-12", "2025-02-12", "hehe", "hehe", std.getById("S1004"), std.getById("S1003"), "images/avatar/anh.jpg");
        //System.out.println(dap.filterContract("", "", "2025-3-27").size());
        System.out.println(dap.getallimgbyID("26"));
    }
}
