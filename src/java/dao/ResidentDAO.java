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
import model.Role;
import util.Util;
import model.Account;
import model.Role;

import model.Role;

/**
 *
 * @author thanh
 */
public class ResidentDAO extends DBContext {

    public boolean checkConnection() {
        return connection == null;
    }
    
    public Resident getById_v2(String pId) {
        String sql = "select  * from resident where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String bod = rs.getDate("bod").toString();
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String cccd = rs.getString("cccd");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Role role = new Role("1", "resident", "--");
                String status = String.valueOf(rs.getInt("active"));
                String gender = rs.getString("gender");
                String image = rs.getString("image");
                Resident resident = new Resident(id, name, cccd, phone, email, bod, address, username, password, status, name, role, image);
                Resident re = new Resident(id, name, cccd, phone, email, bod, address, image, gender, username);

                return re;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Resident> getAll() {
        String sql = "select  * from resident";

        List<Resident> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String bod = rs.getDate("bod").toString();
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String cccd = rs.getString("cccd");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Role role = new Role("1", "resident", "--");
                String status = String.valueOf(rs.getInt("active"));
                String gender = rs.getString("gender");
                String image = rs.getString("image");
                Resident resident = new Resident(id, name, cccd, phone, email, bod, address, username, password, status, name, role, image);
                list.add(resident);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Resident getResidentByUsername(String username) {
        String sql = "select * from Resident where username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String na = rs.getString("name");
                String bod = rs.getDate("bod").toString();
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String cccd = rs.getString("cccd");
                String usernameE = rs.getString("username");
                String password = rs.getString("password");
                Role role = new Role("1", "resident", "--");
                String st = String.valueOf(rs.getInt("active"));
                String gender = rs.getString("gender");
                String image = rs.getString("image");
                Resident resident = new Resident(id, na, cccd, phone, email, bod, address, usernameE, password, st, null, role, image);
                resident.setGender(gender);
                boolean isHomeOwner = rs.getString("isHomeOwner") == "1" ? true : false;
                resident.setIsHomeOwner(isHomeOwner);
                return resident;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
        public Resident getResidentById(String Cccd) {
        String sql = "select * from Resident where cccd = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Cccd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String na = rs.getString("name");
                String bod = rs.getDate("bod").toString();
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String cccd = rs.getString("cccd");
                String usernameE = rs.getString("username");
                String password = rs.getString("password");
                Role role = new Role("1", "resident", "--");
                String st = String.valueOf(rs.getInt("active"));
                String gender = rs.getString("gender");
                String image = rs.getString("image");
                Resident resident = new Resident(id, na, cccd, phone, email, bod, address, usernameE, password, st, null, role, image);
                resident.setGender(gender);
                boolean isHomeOwner = rs.getString("isHomeOwner") == "1" ? true : false;
                resident.setIsHomeOwner(isHomeOwner);
                return resident;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Resident getById(String pId) {
        String sql = "select  * from resident where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String bod = rs.getDate("bod").toString();
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String cccd = rs.getString("cccd");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Role role = new Role("1", "resident", "--");
                String status = String.valueOf(rs.getInt("active"));
                String gender = rs.getString("gender");
                String image = rs.getString("image");
                Resident resident = new Resident(id, name, cccd, phone, email, bod, address, username, password, status, name, role, image);
                resident.setGender(gender);
                boolean isHomeOwner = rs.getString("isHomeOwner") == "1" ? true : false;
                return resident;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Resident> pagingResident(int n) {
        String sql = "select * from Resident order by Id offset ? rows fetch next 3 rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (n * 10));
            List<Resident> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //(String pId, String name, String cccd, String phone, String email, String bod, String address, String status
                String id = rs.getString("id");
                String name = rs.getString("name");
                String bod = rs.getDate("bod").toString();
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String cccd = rs.getString("cccd");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Role role = new Role("1", "resident", "--");
                String status = String.valueOf(rs.getInt("active"));
                String gender = rs.getString("gender");
                String img = rs.getString("image");
                Resident resident = new Resident(id, name, cccd, phone, email, bod, address, username, password, status, null, role, img);
                boolean isHomeOwner = rs.getString("isHomeOwner") == "1" ? true : false;
                resident.setIsHomeOwner(isHomeOwner);
                list.add(resident);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String getEmailByUserName(String username) {
        String sql = "select email from Resident where username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Account> getAllResidentAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "select username, password, email, id, roleId from Resident";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("id"), rs.getInt("roleid"));
                list.add(a);
            }
            return list;

        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void changPasswordById(String id, String newpw) {
        String sql = "update resident set password = ? where id =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newpw);
            st.setString(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean EditProfileRe(Resident r) {
        String sql = "UPDATE [dbo].[Resident]\n"
                + "   SET\n"
                + "      [Email] = ?,\n"
                + "      [Phone] = ?,\n"
                + "      [Address] = ?,\n"
                + "      [image] = ?\n"
                + " WHERE id=?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, r.getEmail());
            pre.setString(2, r.getPhone());
            pre.setString(3, r.getAddress());
            pre.setString(4, r.getImage());
            pre.setString(5, r.getpId());
            pre.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
//    Resident(String pId, String name, String cccd, String phone, String email, String bod, String address, String status)

    public List<Resident> getAllResident() {
        String sql = "select  * from resident where active = 1";

        List<Resident> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String bod = rs.getDate("bod").toString();
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String cccd = rs.getString("cccd");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Role role = new Role("1", "resident", "--");
                String status = String.valueOf(rs.getInt("active"));
                String gender = rs.getString("gender");
                String image = rs.getString("image");
                boolean isHomeOwner = rs.getString("isHomeOwner") == "1" ? true : false;
                Resident resident = new Resident(id, name, cccd, phone, email, bod, address, status, gender);
                resident.setIsHomeOwner(isHomeOwner);
                list.add(resident);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void deleteResident(String pId) {
        String sql = "delete Resident where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int insertNewResident(Resident r) {
        String sql = "INSERT INTO [dbo].[Resident]\n"
                + "           ([Id]\n"
                + "           ,[Name]\n"
                + "           ,[Bod]\n"
                + "           ,[Email]\n"
                + "           ,[Phone]\n"
                + "           ,[Address]\n"
                + "           ,[CCCD]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[roleId]\n"
                + "           ,[active]\n"
                + "           ,[gender]\n"
                + "           ,[image]\n"
                + "           ,[isHomeOwner])\n "
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Util u = new Util();
        List<Resident> listResident = getAll();
        int lastID = u.getNumberFromText(listResident.get(listResident.size() - 1).getpId());
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "P" + (lastID + 1));
            st.setString(2, r.getName());
            st.setString(3, r.getBod());
            if (r.getEmail() == null || r.getEmail().trim().isEmpty()) {
                st.setNull(4, java.sql.Types.VARCHAR);
            } else {
                st.setString(4, r.getEmail());
            }
            if (r.getPhone() == null || r.getPhone().trim().isEmpty()) {
                st.setNull(5, java.sql.Types.VARCHAR);
            } else {
                st.setString(5, r.getPhone());
            }
            st.setString(6, r.getAddress());
            if (r.getCccd() == null || r.getCccd().trim().isEmpty()) {
                st.setNull(7, java.sql.Types.VARCHAR);
            } else {
                st.setString(7, r.getCccd());
            }

            if (r.getUsername() == null || r.getUsername().trim().isEmpty()) {
                st.setNull(8, java.sql.Types.VARCHAR); // Pass NULL if username is null or empty
            } else {
                st.setString(8, r.getUsername());
            }

            st.setString(9, r.getPassword());
            st.setString(10, r.getRole().getId());
            if (r.getUsername() != null) {
                st.setInt(11, 2);
            } else {
                st.setInt(11, 1);
            }
            st.setString(12, r.getGender());
            st.setString(13, "images/avatar/person.jpg");
            if (r.isIsHomeOwner()) {
                st.setInt(14, 2);
            } else {
                st.setInt(14, 1);
            }
            st.executeUpdate();
            return 0;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public boolean checkDuplicateEmail(String email, String reId) {
        String sql = "select * from Resident where Email=? and id not like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, reId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkDuplicatePhone(String phone, String reId) {
        String sql = "SELECT * FROM Resident WHERE Phone = ? AND id NOT LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, phone);
            st.setString(2, reId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkDuplicateID(String id, String reId) {
        String sql = "SELECT * FROM Resident WHERE Cccd = ? AND id NOT LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, reId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkDuplicateUser(String user, String reId) {
        String sql = "SELECT * FROM Resident WHERE Username = ? AND id NOT LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, reId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public List<Resident> getPageByNumber(List<Resident> list, int page, int number) {
        List<Resident> listpage = new ArrayList<>();
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

    public List<Resident> filterListResident(String name, String status, String homeOwner) {
        String sql = "SELECT * FROM resident WHERE 1=1 ";

        if (name != null && !name.isEmpty()) {
            sql += "AND name LIKE N'%" + name + "%' ";
        }
        if (status != null && !status.isEmpty()) {
            sql += "AND active = " + status + " ";
        }
        if (homeOwner != null && !homeOwner.isEmpty()) {
            if ("1".equals(homeOwner)) {
                sql += "And isHomeOwner=1";
            } else {
                sql += "And isHomeOwner=0";
            }
        }

        sql += "ORDER BY id DESC";

        try {
            List<Resident> list = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            LivingApartmentDAO lad = new LivingApartmentDAO();
            while (rs.next()) {
                String id = rs.getString("id");
                String na = rs.getString("name");
                String bod = rs.getDate("bod").toString();
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String cccd = rs.getString("cccd");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Role role = new Role("1", "resident", "--");
                String st = String.valueOf(rs.getInt("active"));
                String gender = rs.getString("gender");
                String image = rs.getString("image");
                Resident resident = new Resident(id, na, cccd, phone, email, bod, address, username, password, st, name, role, image);
                resident.setGender(gender);
                boolean isHomeOwner = rs.getString("isHomeOwner") == "1" ? true : false;
                resident.setIsHomeOwner(isHomeOwner);
                resident.setApartmentNumber(lad.getApartmentsByResidentId(id));
                list.add(resident);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean editResidentStatus(String id, String status) {
        String sql = "UPDATE [dbo].[Resident]\n"
                + "   SET active=? \n"
                + " WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setString(2, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;

    }

    public boolean updateRE(Resident r) {
        String sql = "UPDATE [dbo].[Resident]\n"
                + "   SET \n"
                + "      [Name] = ?\n"
                + "      ,[Bod] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[CCCD] = ?\n"
                + "      ,[username] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[active] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[isHomeOwner] = ?\n"
                + " WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, r.getName());
            st.setString(2, r.getBod());
            st.setString(3, r.getEmail());
            st.setString(4, r.getPhone());
            st.setString(5, r.getAddress());
            st.setString(6, r.getCccd());
            st.setString(7, r.getUsername());
            st.setString(8, r.getPassword());
            st.setString(9, r.getStatus());
            st.setString(10, r.getGender());
            st.setInt(11, r.isIsHomeOwner() == true ? 1 : 0);
            st.setString(12, r.getpId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getNumberLivingPerson() {
        String sql = "select sum(NoPerson) as noperson from Apartment";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("noperson");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getNumberUsingRoom() {
        String sql = "select count(Id) as nousingroom  from Apartment where NoPerson > 0";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("nousingroom");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getNumberNotUsingRoom() {
        String sql = "select count(Id) as notusingroom  from Apartment where NoPerson = 0";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("notusingroom");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getNoUsingRoomByMonthAndYear(int month, int year) {
        String sql = "select count(distinct(aid)) as no from LivingAparment where (year(Startdate) =?  and MONTH(Startdate) <=? )and (MONTH(Enddate) >= ? or Enddate is null)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, year);
            st.setInt(2, month);
            st.setInt(3, month);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("no");
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Integer> getNoUsingRoomByYear(int year) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(getNoUsingRoomByMonthAndYear(i, year));
        }
        return list;
    }

    public int getStartYear() {
        String sql = "select min(year(Startdate)) as year from LivingAparment";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("year");
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public static void main(String[] args) {
        ResidentDAO dao = new ResidentDAO();
        Resident r=dao.getById("P116");
        r.setName("thanh");
        dao.updateRE(r);

    }
}
