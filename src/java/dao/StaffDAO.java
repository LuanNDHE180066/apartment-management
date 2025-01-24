/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Resident;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBContext;
import model.Account;
import model.Company;
import model.Employee;
import model.Role;
import model.Company;
import model.RequestType;
import model.Staff;
import util.Util;

/**
 *
 * @author admin1711
 */
public class StaffDAO extends DBContext {

    public List<Staff> getAll() {
        CompanyDAO sd = new CompanyDAO();
        RoleDAO rd = new RoleDAO();
        String sql = "select * from Staff";
        List<Staff> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("Name");
                String bod = rs.getDate("bod").toString();
                String Email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String cccd = rs.getString("cccd");
                int salary = rs.getInt("salary");
                String education = rs.getString("education");
                String bank = rs.getString("bank");
                int status = rs.getInt("status");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Role r = rd.getById(rs.getString("roleid"));
                Company cp = sd.getById(rs.getString("companyId"));
                String startDate = rs.getString("startdate");
                String enddate = rs.getString("enddate");
                Staff s = new Staff(id, name, bod, Email, phone, address, cccd, salary, education, bank, status, username, password, r, cp, startDate, enddate);
                list.add(s);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Staff getById(String id) {
        List<Staff> list = this.getAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                return list.get(i);
            }
        }
        return null;
    }

    public void EditProfileSt(String id, String phone, String email, String address) {
        String sql = "update Staff set Email=?, Phone=?, [Address]=? where id=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, phone);
            pre.setString(3, address);
            pre.setString(4, id);
            pre.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Account> getAllStaffAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "select username, password, email, id,roleId from Staff";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                list.add(a);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateStaffInfor(Staff s) {
        String sql = "Update staff set name = ?, bod = ? ,email = ? , phone = ?, address = ? , cccd = ? , salary = ? , education = ? , bank = ?"
                + ", status = ? ,roleid = ? ,companyid = ?, startdate = ?, enddate = ? where id = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getName());
            st.setString(2, s.getBod());
            st.setString(3, s.getEmail());
            st.setString(4, s.getPhone());
            st.setString(5, s.getAddress());
            st.setString(6, s.getCccd());
            st.setInt(7, s.getSalary());
            st.setString(8, s.getEducation());
            st.setString(9, s.getBank());
            st.setInt(10, s.getStatus());
            st.setString(11, s.getRole().getId());
            st.setString(12, s.getCompany().getId());
            st.setString(13, s.getStartDate());
            st.setString(14, s.getEndDate());
            st.setString(15, s.getId());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;

    }

    public boolean insertStaff(Staff s) {
        String sql = "insert into Staff(id,Name, bod, email, phone, Address, cccd,Salary,Education,Bank,status,username,password,roleId, companyid,startdate) \n"
                + "values(?,?,?,?,?,?,?,?,?,?,1,?,?,?,?,?)";
        Util u = new Util();
        List<Staff> list = this.getAll();
        String newId = "";
        if (list.isEmpty()) {
            newId = "S001";
        } else {
            int lastIdNum = u.getNumberFromText(list.get(list.size() - 1).getId()) + 1;
            if (lastIdNum <= 9) {
                newId = "S00" + lastIdNum;
            } else if (lastIdNum >= 10 && lastIdNum <= 99) {
                newId = "S0" + lastIdNum;
            } else {
                newId = "S" + lastIdNum;
            }
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newId);
            ps.setString(2, s.getName());
            ps.setString(3, s.getBod());
            ps.setString(4, s.getEmail());
            ps.setString(5, s.getPhone());
            ps.setString(6, s.getAddress());
            ps.setString(7, s.getCccd());
            ps.setInt(8, s.getSalary());
            ps.setString(9, s.getEducation());
            ps.setString(10, s.getBank());
            ps.setString(11, s.getUsername());
            ps.setString(12, s.getPassword());
            ps.setString(13, s.getRole().getId());
            ps.setString(14, s.getCompany().getId());
            ps.setString(15, s.getStartDate());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Staff> searchByName(List<Staff> list, String name) {
        List<Staff> rs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                rs.add(list.get(i));
            }
        }
        return rs;
    }

    public List<Staff> getPageByNumber(List<Staff> list, int page, int number) {
        List<Staff> listpage = new ArrayList<>();
        int start = number * (page - 1);
        int end = number * page - 1;
        for (int i = start; i <= end; i++) {
            listpage.add(list.get(i));
            if (i == list.size() - 1) {
                break;
            }
        }
        return listpage;
    }

    public List<Staff> getByStatus(int status) {
        if (status == -1) {
            return this.getAll();
        }
        List<Staff> list = this.getAll();
        List<Staff> rs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus() == status) {
                rs.add(list.get(i));
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        Role role = new Role();
        role.setId("2"); // Set role ID

        // Create a company object (assuming Company class exists)
        Company company = new Company();
        company.setId("C001"); // Set company ID

        // Create the Staff object
        Staff staff = new Staff();
        staff.setId("S001"); // or leave it empty; it will be generated in your method
        staff.setName("John Doe");
        staff.setBod("1990-01-01"); // Format as needed
        staff.setEmail("johndoe@example.com");
        staff.setPhone("1234567890");
        staff.setAddress("123 Main St, Anytown, USA");
        staff.setCccd("CCCD123456");
        staff.setSalary(50000);
        staff.setEducation("Bachelor's Degree");
        staff.setBank("Bank Name");
        staff.setUsername("johndoe");
        staff.setPassword("securePassword");
        staff.setRole(role);
        staff.setCompany(company);
        staff.setStartDate("2023-01-01"); // Format as needed

        // Now you can use this staff object to insert it into the database
        StaffDAO staffDAO = new StaffDAO();
        boolean isInserted = staffDAO.insertStaff(staff);
        System.out.println("Staff inserted: " + isInserted);
    }
    public Staff getByRequestType(RequestType rt){
        List<Staff> list  =this.getAll();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getRole().getId().equalsIgnoreCase(rt.getId())
                    && list.get(i).getStatus()==1) return list.get(i);
        }
        return null;
    }

}
