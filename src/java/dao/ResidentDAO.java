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
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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

    public boolean setUsernameAndPassword(String username, String password, String id) {
        String sql = "update Resident set username = ? , password = ? where Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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

    public void setNullUsernameAndPassword(String rid) {
        String sql = "Update Resident set password = null, username = null where Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, rid);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                //boolean isHomeOwner = rs.getString("isHomeOwner") == "1" ? true : false;
                return resident;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Resident> getByMultipleID(String[] pIds) {
        // If array is null or empty, return empty list
        if (pIds == null || pIds.length == 0) {
            return new ArrayList<>();
        }

        // Build the SQL query with dynamic number of placeholders
        String placeholders = String.join(",", Collections.nCopies(pIds.length, "?"));
        String sql = "SELECT * FROM resident WHERE id IN (" + placeholders + ")";

        List<Resident> residents = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            // Set all ID parameters
            for (int i = 0; i < pIds.length; i++) {
                st.setString(i + 1, pIds[i]);
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String bod = rs.getDate("bod") != null ? rs.getDate("bod").toString() : null;
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

                Resident resident = new Resident(id, name, cccd, phone, email, bod,
                        address, username, password, status, name, role, image);
                resident.setGender(gender);

                residents.add(resident);
            }

            return residents;
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return residents; // Return whatever we have even if there's an error
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

                Resident resident = new Resident(id, name, cccd, phone, email, bod, address, status, gender);

                list.add(resident);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void deleteResident(String pId) {
        String sql2 = "DELETE FROM LivingAparment WHERE rId=?"; // Execute first
        String sql1 = "DELETE FROM Resident WHERE id=?"; // Execute second

        try {
            // Use auto-commit off to ensure both queries succeed together
            connection.setAutoCommit(false);

            // First delete from LivingAparment
            try (PreparedStatement ps2 = connection.prepareStatement(sql2)) {
                ps2.setString(1, pId);
                ps2.executeUpdate();
            }

            // Then delete from Resident
            try (PreparedStatement ps1 = connection.prepareStatement(sql1)) {
                ps1.setString(1, pId);
                ps1.executeUpdate();
            }

            // Commit both deletions
            connection.commit();
        } catch (SQLException ex) {
            try {
                // Rollback in case of error
                connection.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, "Rollback failed", rollbackEx);
            }
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                // Restore auto-commit mode
                connection.setAutoCommit(true);
            } catch (SQLException autoCommitEx) {
                Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, "Failed to restore auto-commit", autoCommitEx);
            }
        }
    }

    public String insertNewResident(Resident r) {
        String sql = "INSERT INTO [dbo].[Resident] (\n"
                + "    [Id],\n"
                + "    [Name],\n"
                + "    [Bod],\n"
                + "    [Email],\n"
                + "    [Phone],\n"
                + "    [Address],\n"
                + "    [CCCD],\n"
                + "    [username],\n"
                + "    [password],\n"
                + "    [roleId],\n"
                + "    [active],\n"
                + "    [gender],\n"
                + "    [image]\n"
                + ") VALUES (\n"
                + "    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?\n"
                + ")";

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

            st.executeUpdate();
            return "P" + (lastID + 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> insertMultipleResidents(List<Resident> residents) {
        String sql = "INSERT INTO [dbo].[Resident] (\n"
                + "    [Id],\n"
                + "    [Name],\n"
                + "    [Bod],\n"
                + "    [Email],\n"
                + "    [Phone],\n"
                + "    [Address],\n"
                + "    [CCCD],\n"
                + "    [username],\n"
                + "    [password],\n"
                + "    [roleId],\n"
                + "    [active],\n"
                + "    [gender],\n"
                + "    [image]\n"
                + ") VALUES (\n"
                + "    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?\n"
                + ")";

        Util u = new Util();
        List<Resident> listResident = getAll();
        int lastID = u.getNumberFromText(listResident.get(listResident.size() - 1).getpId());
        List<String> insertedIds = new ArrayList<>();

        try {
            connection.setAutoCommit(false); // Start transaction
            PreparedStatement st = connection.prepareStatement(sql);

            for (Resident r : residents) {
                lastID++; // Increment ID for each resident
                String newId = "P" + lastID;

                st.setString(1, newId);
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
                    st.setNull(8, java.sql.Types.VARCHAR);
                } else {
                    st.setString(8, r.getUsername());
                }
                st.setString(9, r.getPassword());
                st.setString(10, r.getRole().getId());
                if (r.getUsername() != null) {
                    st.setInt(11, 2); // Active status 2 for representatives
                } else {
                    st.setInt(11, 1); // Active status 1 for non-representatives
                }
                st.setString(12, r.getGender());
                st.setString(13, "images/avatar/person.jpg");

                st.addBatch(); // Add to batch
                insertedIds.add(newId);
            }

            st.executeBatch(); // Execute all insertions
            connection.commit(); // Commit transaction
            return insertedIds;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback(); // Rollback on error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return null;
        } finally {
            try {
                connection.setAutoCommit(true); // Reset auto-commit
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    public boolean checkDuplicateEmail(String email) {
        String sql = "select * from Resident where Email=? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
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

    public boolean checkDuplicatePhone(String phone) {
        String sql = "SELECT * FROM Resident WHERE Phone = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, phone);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkDuplicatateUsername(String username) {
        String sql = "select * from Resident where username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkDuplicateID(String id, String reId) {
        String sql = "SELECT * FROM Resident WHERE CCCD = ? AND id NOT LIKE ?";
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

    public boolean checkDuplicateID(String id) {
        String sql = "SELECT * FROM Resident WHERE CCCD = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
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

    public List<Resident> filterListResident(String name, String status, String aptNumber) {
        String sql = "SELECT * FROM resident WHERE 1=1";
        List<Resident> resultList = new ArrayList<>();
        LivingApartmentDAO lad = new LivingApartmentDAO();

        // Build SQL query with name and status filters
        if (name != null && !name.isEmpty()) {
            sql += " AND name LIKE ?";
        }
        if (status != null && !status.isEmpty()) {
            sql += " AND active = ?";
        }
        sql += " ORDER BY id DESC";

        try {
            // Prepare statement with parameters to prevent SQL injection
            PreparedStatement ps = connection.prepareStatement(sql);
            int paramIndex = 1;

            if (name != null && !name.isEmpty()) {
                ps.setString(paramIndex++, "%" + name + "%");
            }
            if (status != null && !status.isEmpty()) {
                ps.setInt(paramIndex++, Integer.parseInt(status));
            }

            // Get base resident list
            ResultSet rs = ps.executeQuery();
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

                Resident resident = new Resident(id, na, cccd, phone, email, bod, address,
                        username, password, st, na, role, image);
                resident.setGender(gender);
                resultList.add(resident);
            }

            // Apply apartment filter if specified
            if (aptNumber != null && !aptNumber.isEmpty()) {
                List<Resident> residentApt = lad.getLivingResidentList(aptNumber);
                if (!residentApt.isEmpty()) {
                    // Keep only residents whose IDs match with residentApt list
                    resultList.retainAll(residentApt.stream()
                            .filter(aptResident -> resultList.stream()
                            .anyMatch(r -> r.getpId().equals(aptResident.getpId())))
                            .collect(Collectors.toList()));
                } else {
                    // If no residents found for this apartment, clear the list
                    resultList.clear();
                }
            }

            return resultList;

        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
            st.setString(11, r.getpId());
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
//        System.out.println(dao.editResidentStatus("P100", "2"));
       dao.deleteResident("P130");

    }
}
