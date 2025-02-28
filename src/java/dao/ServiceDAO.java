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

    public List<Service> searchByName(String name) {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT * FROM Service WHERE Name LIKE ?"; // Query fixed

        CompanyDAO cd = new CompanyDAO();
        CategoryServiceDAO csd = new CategoryServiceDAO();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, "%" + name + "%"); // Parameterized query to prevent SQL injection

            ResultSet rs = st.executeQuery();
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
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return list;
    }

    public List<Service> filterByCompanyAndCategoryAndStatus(String category, String company, String status) {
        String sql = "Select * from Service where 1=1";
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

    public static void main(String[] args) {
        ServiceDAO sd = new ServiceDAO();
        List<Service> list = sd.getAll();
        System.out.println(sd.filterByCompanyAndCategoryAndStatus(null, "", ""));
    }
}
