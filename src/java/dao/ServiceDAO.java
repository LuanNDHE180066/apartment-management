/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.DBContext;
import model.CategoryService;
import model.Company;
import model.Service;

/**
 *
 * @author Lenovo
 */
public class ServiceDAO extends DBContext {

    public List<Service> getAll() {
        String sql = "Select * from Service";
        List<Service> list = new ArrayList<>();
        CompanyDAO cd = new CompanyDAO();
        CategoryServiceDAO csd = new CategoryServiceDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                int price = rs.getInt("unitprice");
                String des = rs.getString("description");
                CategoryService ct = csd.getByCategoryId(rs.getString("scid"));
                Company c = cd.getById(rs.getString("cid"));
                int status = rs.getInt("status");
                Service s = new Service(id, name, price, des, ct, c, status);
                list.add(s);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

//    public List<Service> searchByName(String name) {
//        List<Service> list = new ArrayList<>();
//        String sql = "SELECT * FROM Service WHERE Name LIKE ?"; // Query fixed
//
//        CompanyDAO cd = new CompanyDAO();
//        CategoryServiceDAO csd = new CategoryServiceDAO();
//
//        try (PreparedStatement st = connection.prepareStatement(sql)) {
//            st.setString(1, "%" + name + "%"); // Parameterized query to prevent SQL injection
//
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                Service s = new Service();
//                s.setId(rs.getString(1));
//                s.setName(rs.getString(2));
//                s.setUnitPrice(rs.getFloat(3));
//                s.setDescription(rs.getString(4));
//                s.setCategoryService(csd.getByServiceId(s.getId()));
//                s.setCompany(cd.getByServiceId(s.getId()));
//                s.setStatus(rs.getInt(7));
//                list.add(s);
//            }
//        } catch (SQLException e) {
//            System.out.println("SQL Error: " + e.getMessage());
//        }
//        return list;
//    }
    public List<Service> filterByNameAndCompanyAndCategoryAndStatus(String name, String category, String company, String status) {
        String sql = "Select * from Service where name like '%" + name + "%'";
        List<Service> list = new ArrayList<>();
        CompanyDAO cd = new CompanyDAO();
        CategoryServiceDAO csd = new CategoryServiceDAO();
        if (category != null && !category.isEmpty()) {
            sql += " And scId= '" + category + "'";
        }
        if (company != null && !company.isEmpty()) {
            sql += " And cId= '" + company + "'";
        }
        if (status != null && !status.isEmpty()) {
            sql += " And status= '" + status + "'";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            System.out.println(sql);
            while (rs.next()) {
                Service s = new Service();
                s.setId(rs.getString(1));
                s.setName(rs.getString(2));
                s.setUnitPrice(rs.getFloat(3));
                s.setDescription(rs.getString(4));
                s.setCategoryService(csd.getByServiceId(s.getId()));
                s.setCompany(cd.getByServiceId(s.getId()));
                s.setStatus(rs.getInt(7));

                list.add(s);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    public boolean isExistName(String name) {
        String sql = "select * from service where name =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public int getNumberService() {
        String sql = "select count(*) as no from service";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("no");
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void addService(String name,int price,String des,String type,String company,int status) {
        String sql = "INSERT INTO [dbo].[Service] (Id, Name, UnitPrice, Description, scId, cId, Status)\n"
                + "VALUES\n"
                + "(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, "SVC"+(this.getNumberService()+1));
            st.setString(2, name);
            st.setInt(3, price);
            st.setString(4, des);
            st.setString(5, type);
            st.setString(6, company);
            st.setString(7, Integer.toString(status));
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ServiceDAO sd = new ServiceDAO();
        List<Service> list = sd.getAll();
    }
}
