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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
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
                double price = rs.getDouble("unitprice");
                String des = rs.getString("description");
                CategoryService ct = csd.getByCategoryId(rs.getString("scid"));
                Company c = cd.getById(rs.getString("cid"));
                int status = rs.getInt("status");
                Service s = new Service(id, name, price, des, ct, c, status);
                s.setStartDate(rs.getDate("startdate").toString());
                if (rs.getDate("enddate") != null) {
                    s.setEndDate(rs.getDate("enddate").toString());
                }
                s.setUnit(rs.getString("unit"));
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
                s.setStartDate(rs.getDate("startdate").toString());
                if (rs.getDate("enddate") != null) {
                    s.setEndDate(rs.getDate("enddate").toString());
                }
                s.setUnit(rs.getString("unit"));
                list.add(s);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

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

    public String addService(String name, float price, String des, String type, String company, int status, String unit) {
        String sql = "INSERT INTO [dbo].[Service] (Id, Name, UnitPrice, Description, scId, cId, Status,startDate,unit)\n"
                + "VALUES\n"
                + "(?, ?, ?, ?, ?, ?, ?,?,?)";
        String newServiceId = "SVC" + (this.getNumberService() + 1);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newServiceId);
            st.setString(2, name);
            st.setFloat(3, price);
            st.setString(4, des);
            st.setString(5, type);
            st.setString(6, company);
            st.setString(7, Integer.toString(status));
            st.setDate(8, java.sql.Date.valueOf(LocalDate.now()));
            st.setString(9, unit);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return newServiceId;
    }

    public Service getById(String id) {
        String sql = "Select * from Service where id =?";
        CompanyDAO cd = new CompanyDAO();
        CategoryServiceDAO csd = new CategoryServiceDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                float price = rs.getFloat("unitprice");
                String des = rs.getString("description");
                CategoryService ct = csd.getByCategoryId(rs.getString("scid"));
                Company c = cd.getById(rs.getString("cid"));
                int status = rs.getInt("status");
                Service s = new Service(id, name, price, des, ct, c, status);
                s.setStartDate(rs.getDate("startdate").toString());
                if (rs.getDate("enddate") != null) {
                    s.setEndDate(rs.getDate("enddate").toString());
                }
                s.setUnit(rs.getString("unit"));
                return s;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void turnToInActive(String id) {
        String sql = "update service set status = 0, enddate =? where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            st.setString(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getNumberUsedServiceId(String id) {
        String sql = "select count(*) as no from MonthlyInvoice where sid =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("no");
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public float getPercentOfUsingService(String sid) {
        LivingApartmentDAO ld = new LivingApartmentDAO();
        return (float) this.getNumberUsedServiceId(sid) / ld.getNumberLivingResident() * 100;
    }

    public int getNumberUsedByTime(int year, int month, String sid) {
        String sql = "select count(*) as no from InvoiceDetail id join Invoice i on id.invoiceId=i.id "
                + "where year(i.invoicedate)=? "
                + "and month(i.invoicedate)=?"
                + " and id.serviceId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, year);
            st.setInt(2, month);
            st.setString(3, sid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("no");
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public List<Integer> getNumberUsedAllMonth(int year, String sid) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 11; i++) {
            int num = this.getNumberUsedByTime(year, i + 1, sid);
            list.add(num);
        }
        return list;
    }

    public List<Float> getPercentUsedAllMonth(int year, String sid) {
        List<Float> list = new ArrayList<>();
        LivingApartmentDAO ld = new LivingApartmentDAO();
        for (int i = 0; i <= 11; i++) {
            if (i + 1 > LocalDate.now().getMonthValue()) {
                break;
            }
            float percent = (this.getNumberUsedByTime(year, i + 1, sid) / (float) ld.getNumberLivingByTime(i + 1, year)) * 100;
            list.add(percent);
        }
        return list;
    }
    public String getServiceEVNId(){
        String sql="select * from service where Name =N'Tiền điện' and status =1";
        try {
            PreparedStatement st =connection.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            if(rs.next()) return rs.getString("id");
        } catch (SQLException e) {
        }
        return null;
    }
      public String getServiceWVNId(){
        String sql="select * from service where Name =N'Tiền nước' and status =1";
        try {
            PreparedStatement st =connection.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            if(rs.next()) return rs.getString("id");
        } catch (SQLException e) {
        }
        return null;
    }
    public Date getLastInvoice() {
        String sql = "select * from invoice order by invoicedate desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDate("invoicedate");
            }
        } catch (SQLException e) {
        }
        return null;
    }


    public static void main(String[] args) {
        ServiceDAO sd = new ServiceDAO();
        System.out.println(sd.getNumberUsedByTime(2025, 1, "SVC1"));
        System.out.println(Arrays.toString(sd.getPercentUsedAllMonth(2025, "SV001").toArray()));
    }
}
