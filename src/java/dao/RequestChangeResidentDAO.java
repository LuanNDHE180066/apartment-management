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

    public void addNewRequestChange(RequestChangeResident re) {
        String sql = "INSERT INTO [dbo].[RequestChangeResident] ("
                + "[owner_name], [new_person_name], [dob], [address], "
                + "[phone], [email], [cccd], [username], [password], "
                + "[gender], [room_number], [change_type], "
                + "[new_person_exists], [admin_status], [created_at]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            ps.setString(15, re.getCreatedAt());
            ps.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(RequestChangeResidentDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
    }
}
