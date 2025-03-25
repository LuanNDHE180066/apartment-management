/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jdbc.DBContext;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import jdbc.DBContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jdbc.DBContext;
import model.Apartment;
import model.LivingApartment;
import model.OwnerApartment;
import model.RequestChangeResident;
import model.Resident;

/**
 *
 * @author quang
 */
public class RequestChangeResidentDAO extends DBContext {

    private ResidentDAO residentDAO = new ResidentDAO();

    private RoleDAO roleDAO = new RoleDAO();

    public void addNewRequestChange(RequestChangeResident re) {
        String sql = "INSERT INTO [dbo].[RequestChangeResident] ("
                + "[owner_name], [new_person_name], [dob], [address], "
                + "[phone], [email], [cccd], [username], [password], "
                + "[gender], [room_number], [change_type], "
                + "[new_person_exists], [admin_status], [created_at]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = currentDateTime.format(formatter);

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, re.getOwner().getpId());
            ps.setString(2, re.getNewPerson().getName());
            ps.setString(3, re.getNewPerson().getBod());
            ps.setString(4, re.getNewPerson().getAddress());
            ps.setString(5, re.getNewPerson().getPhone());
            ps.setString(6, re.getNewPerson().getEmail());
            ps.setString(7, re.getNewPerson().getCccd());
            ps.setString(8, re.getNewPerson().getUsername());
            ps.setString(9, re.getNewPerson().getPassword());
            ps.setString(10, re.getNewPerson().getGender());
            ps.setString(11, re.getRoomNumber());
            ps.setInt(12, re.getChangeType());
            ps.setInt(13, re.getNewPersonExists());
            ps.setInt(14, re.getAdminStatus());
            ps.setString(15, formattedDate);
            ps.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(RequestChangeResidentDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public List<RequestChangeResident> getPendingChangeRequest() {
        String sql = "select * from requestchangeresident where admin_status = 0";
        List<RequestChangeResident> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("request_id");
                String ownerId = rs.getString("owner_name");
                String newPersonId = rs.getString("new_person_name");
                String dob = rs.getString("dob");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String cccd = rs.getString("cccd");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String gender = rs.getString("gender");
                String roomId = rs.getString("room_number");
                int changeType = rs.getInt("change_type");
                int isExistPerson = rs.getInt("new_person_exists");
                int admin_status = rs.getInt("admin_status");
                String created_at = rs.getString("created_at");

                Resident r = new Resident(newPersonId, cccd, phone, email, dob, address, username, null, roleDAO.getById("1"), gender);
                RequestChangeResident rc = new RequestChangeResident(id, residentDAO.getById(ownerId), r,
                        roomId, changeType, isExistPerson, admin_status, created_at);

                list.add(rc);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(RequestChangeResidentDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean updateAdminStatus(String id, String status) {
        String sql = "update requestchangeresident set admin_status = ? where request_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(RequestChangeResidentDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    public RequestChangeResident getRequestChangeById(String id) {
        String sql = "select * from RequestChangeResident where request_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int rid = rs.getInt("request_id");
                String ownerId = rs.getString("owner_name");
                String newPersonId = rs.getString("new_person_name");
                String dob = rs.getString("dob");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String cccd = rs.getString("cccd");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String gender = rs.getString("gender");
                String roomId = rs.getString("room_number");
                int changeType = rs.getInt("change_type");
                int isExistPerson = rs.getInt("new_person_exists");
                int admin_status = rs.getInt("admin_status");
                String created_at = rs.getString("created_at");

                Resident r = new Resident(newPersonId, cccd, phone, email, dob, address, username, null, roleDAO.getById("1"), gender);
                RequestChangeResident rc = new RequestChangeResident(rid, residentDAO.getById(ownerId), r,
                        roomId, changeType, isExistPerson, admin_status, created_at);
                return rc;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(RequestChangeResidentDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {

        ResidentDAO dao = new ResidentDAO();
        Resident owner = dao.getById("P110");
        Resident newRe = dao.getById("P106");
        //System.out.println(newRe.getGender());
        LocalDateTime lc = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = lc.format(format);
        RequestChangeResident re = new RequestChangeResident(owner, newRe,
                "A008", 0, 1, 0, formattedDate);
        RequestChangeResidentDAO daoR = new RequestChangeResidentDAO();
//        System.out.println(daoR.addNewRequestChange(re));
        daoR.addNewRequestChange(re);
    }
}
