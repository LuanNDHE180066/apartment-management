
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
                Company cp = sd.getById(rs.getString("cid"));
                String startDate = rs.getString("startdate");
                String enddate = rs.getString("enddate");
                String gender = rs.getString("gender");
                String image = rs.getString("image");
                Staff s = new Staff(id, name, bod, Email, phone, address, cccd, salary, education, bank, status, username, password, r, cp, startDate, enddate, gender, image);
                list.add(s);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Staff> getWorkingStaff(String role) {
        List<Staff> working = this.getStaffbyStatus();
        List<Staff> staffs = new ArrayList<>();
        String sql = "select * from Staff where id not in (";
        for (int i = 0; i < working.size(); i++) {
            sql += "'" + working.get(i).getId() + "',";
            if (i == working.size() - 1) {
                sql += "'" + working.get(i).getId() + "')";
            }
        }
        sql += " and status = 1 and roleid = ?";
        RoleDAO rd = new RoleDAO();
        CompanyDAO sd = new CompanyDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, role);
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
                Company cp = sd.getById(rs.getString("cid"));
                String startDate = rs.getString("startdate");
                String enddate = rs.getString("enddate");
                String gender = rs.getString("gender");
                Staff s = new Staff(id, name, bod, Email, phone, address, cccd, salary, education, bank, status, username, password, r, cp, startDate, enddate, gender);
                staffs.add(s);
            }
        } catch (Exception e) {
        }
        return staffs;
    }

    public List<Staff> getStaffbyStatus() {
        CompanyDAO sd = new CompanyDAO();
        RoleDAO rd = new RoleDAO();
        String sql = "select distinct Staff.* from Request right join Staff on Request.sId = Staff.Id where Request.Status = '1'";
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
                Company cp = sd.getById(rs.getString("cid"));
                String startDate = rs.getString("startdate");
                String enddate = rs.getString("enddate");
                String gender = rs.getString("gender");
                Staff s = new Staff(id, name, bod, Email, phone, address, cccd, salary, education, bank, status, username, password, r, cp, startDate, enddate, gender);
                list.add(s);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Staff> getActiveStaffbyRole(String role) {
        CompanyDAO sd = new CompanyDAO();
        RoleDAO rd = new RoleDAO();
        String sql = "select * from Staff where roleid=? and status = 1";
        List<Staff> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, role);
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
                Company cp = sd.getById(rs.getString("cid"));
                String startDate = rs.getString("startdate");
                String enddate = rs.getString("enddate");
                String gender = rs.getString("gender");
                Staff s = new Staff(id, name, bod, Email, phone, address, cccd, salary, education, bank, status, username, password, r, cp, startDate, enddate, gender);
                list.add(s);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Staff> getAdminAndAdministrative() {
        CompanyDAO sd = new CompanyDAO();
        RoleDAO rd = new RoleDAO();
        String sql = "select * from Staff where roleid in (0,2)";
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
                Company cp = sd.getById(rs.getString("cid"));
                String startDate = rs.getString("startdate");
                String enddate = rs.getString("enddate");
                String gender = rs.getString("gender");
                Staff s = new Staff(id, name, bod, Email, phone, address, cccd, salary, education, bank, status, username, password, r, cp, startDate, enddate, gender);
                list.add(s);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Staff> getStaffByRequestName(String nametype) {
        CompanyDAO sd = new CompanyDAO();
        RoleDAO rd = new RoleDAO();
        String sql = "select Staff.id as id, Staff.name as name,bod,email,phone,address,cccd,salary,education,bank,status,username,password,roleid,cid,startdate,enddate from Staff join TypeRequest on Staff.roleId = TypeRequest.destination where TypeRequest.name = ?";
        List<Staff> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, nametype);
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
                Company cp = sd.getById(rs.getString("cid"));
                String startDate = rs.getString("startdate");
                String enddate = rs.getString("enddate");
                String gender = rs.getString("gender");
                Staff s = new Staff(id, name, bod, Email, phone, address, cccd, salary, education, bank, status, username, password, r, cp, startDate, enddate, gender);
                list.add(s);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Staff getByRequestType(RequestType rt) {
        List<Staff> list = this.getAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRole().getId().equalsIgnoreCase(rt.getDestination().getId())
                    && list.get(i).getStatus() == 1) {
                return list.get(i);
            }
        }
        return null;
    }

    public Staff getById(String ids) {
        String sql = "select * from Staff where id=?";
        CompanyDAO sd = new CompanyDAO();
        RoleDAO rd = new RoleDAO();
        List<Staff> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ids);
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
                Company cp = sd.getById(rs.getString("cid"));
                String startDate = rs.getString("startdate");
                String enddate = rs.getString("enddate");
                String gender = rs.getString("gender");
                String image = rs.getString("image");
                Staff s = new Staff(id, name, bod, Email, phone, address, cccd, salary, education, bank, status, username, password, r, cp, startDate, enddate, gender, image);
                return s;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void EditProfileSt(Staff s) {
        String sql = "update Staff set Email=?, Phone=?,Bank=?, [Address]=?,[image]=? where id=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s.getEmail());
            pre.setString(2, s.getPhone());
            pre.setString(3, s.getBank());
            pre.setString(4, s.getAddress());
            pre.setString(5, s.getImage());
            pre.setString(6, s.getId());
            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
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
                + ", status = ? ,roleid = ? ,cID = ?, startdate = ?, enddate = ? where id = ? ";
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
            st.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;

    }

    public boolean insertStaff(Staff s) {
        String sql = "insert into Staff(id,Name, bod, email, phone, Address, cccd,Salary,Education,Bank,status,username,password,roleId, cid,startdate,gender,image) \n"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            ps.setInt(11, 1);
            ps.setString(12, s.getUsername());
            ps.setString(13, s.getPassword());
            ps.setString(14, s.getRole().getId());
            ps.setString(15, s.getCompany().getId());
            ps.setString(16, s.getStartDate());
            ps.setString(17, s.getGender());
            ps.setString(18, "images/avatar/person.jpg");
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Staff> searchByName(List<Staff> list, String name) {
        List<Staff> rs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().toLowerCase().contains(name.trim().toLowerCase())) {
                rs.add(list.get(i));
            }
        }
        return rs;
    }

    public List<Staff> getBySearchNameAndStatus(int status, String name) {
        List<Staff> list = new ArrayList<>();
        Util util = new Util();
        CompanyDAO sd = new CompanyDAO();
        RoleDAO rd = new RoleDAO();
        name = util.stringNomalize(name.trim());
        String sql = "select * from staff where 1=1";
        int count = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (!name.isBlank()) {
                sql += " and name like '%" + name + "%'";
            }
            if (status != -1) {
                sql += " and status = " + status;
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String n = rs.getString("Name");
                String bod = rs.getDate("bod").toString();
                String Email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String cccd = rs.getString("cccd");
                int salary = rs.getInt("salary");
                String education = rs.getString("education");
                String bank = rs.getString("bank");
                int s = rs.getInt("status");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Role r = rd.getById(rs.getString("roleid"));
                Company cp = sd.getById(rs.getString("cid"));
                String startDate = rs.getString("startdate");
                String enddate = rs.getString("enddate");
                String gender = rs.getString("gender");
                Staff sf = new Staff(id, n, bod, Email, phone, address, cccd, salary, education, bank, s, username, password, r, cp, startDate, enddate, gender);
                list.add(sf);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println(sql);
        return list;
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
        //List<Staff> list = this.getAll();
        List<Staff> rs = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getStatus() == status) {
//                rs.add(list.get(i));
//            }
//        }
        RoleDAO rd = new RoleDAO();
        CompanyDAO sd = new CompanyDAO();
        String sql = "select * from staff where status=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, status);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("Name");
                String bod = resultSet.getDate("bod").toString();
                String Email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String cccd = resultSet.getString("cccd");
                int salary = resultSet.getInt("salary");
                String education = resultSet.getString("education");
                String bank = resultSet.getString("bank");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Role r = rd.getById(resultSet.getString("roleid"));
                Company cp = sd.getById(resultSet.getString("cid"));
                String startDate = resultSet.getString("startdate");
                String enddate = resultSet.getString("enddate");
                String gender = resultSet.getString("gender");
                String image = resultSet.getString("image");
                Staff s = new Staff(id, name, bod, Email, phone, address, cccd, salary, education, bank, status, username, password, r, cp, startDate, enddate, gender, image);
                rs.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rs;
    }

    public boolean checkDuplicatePhone(String phone) {
        List<Staff> list = getAll();
        for (Staff staff : list) {
            if (staff.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;

    }

    public boolean checkDupEmail(String email) {
        String sql = "select * from staff where Email=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean checkDuplicateEmail(String email) {
        List<Staff> list = getAll();
        for (Staff staff : list) {
            if (staff.getEmail().equals(email)) {
                return true;
            }
        }
        return false;

    }

    public boolean checkDuplicateID(String id) {
        List<Staff> list = getAll();
        for (Staff staff : list) {
            if (staff.getCccd().equals(id)) {
                return true;
            }
        }
        return false;

    }

    public boolean checkDuplicateUserName(String username) {
        List<Staff> list = getAll();
        for (Staff staff : list) {
            if (staff.getUsername().equals(username)) {
                return true;
            }
        }
        return false;

    }

    public boolean checkDuplicateBank(String bank) {
        List<Staff> list = getAll();
        for (Staff staff : list) {
            if (staff.getBank().equals(bank)) {
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {

        StaffDAO dao = new StaffDAO();
        //Staff s1 = new Staff("S1013", "Guard Company", "2000-05-05", "na3m@gmail.com", "0226013325", "Ha Noi", "11232231", 500, "VO Hoc", "1234564898723", 1, "sa1das", "4578", new Role("4", "name", ""), new Company("C001"), "2025-02-01", "F");
        //Staff s = new Staff("S1013", "2000-05-05", "na3m@gmail.com", "0226013325", "Ha Noi", "11232231", 500, "VO Hoc", "1234564898723", "sa1das", "4578", new Role("4", "name", ""), new Company("C001"), "2025-02-01", "F");
        System.out.println("working bảng");
        List<Staff> l = dao.getStaffbyStatus();
        for (Staff staff : l) {
            System.out.println("id:" + staff.getId() + "name:" + staff.getName());
        }
        System.out.println("full bảng");
        List<Staff> l1 = dao.getActiveStaffbyRole("5");
        for (Staff staff : l1) {
            System.out.println("id:" + staff.getId() + "name:" + staff.getName());
        }
        System.out.println("regex bảng");
        l1.removeAll(l);
        for (Staff staff : l1) {
            System.out.println("id:" + staff.getId() + "name:" + staff.getName());
        }
    }

}
