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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import jdbc.DBContext;
import model.Apartment;
import model.LivingApartment;
import model.OwnerApartment;
import model.RepresentResidentChangeRequest;
import model.Resident;

/**
 *
 * @author quang
 */
public class RepresentResidentChangeRequestDAO extends DBContext {

    private ResidentDAO residentDAO = new ResidentDAO();

    public String getNewUsername(String id) {
        String sql = "select newUsername from LivingAparmentRepresentResidentChangeRequest where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("newUsername");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(RepresentResidentChangeRequestDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<RepresentResidentChangeRequest> getAllPendingList() {
        String sql = "select * from LivingAparmentRepresentResidentChangeRequest where admin_approve = 0";
        List<RepresentResidentChangeRequest> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String oldRepresentPerson = rs.getString("oldRepresentPerson");
                String newRepresentPerson = rs.getString("newRepresentPerson");
                String requestDate = rs.getString("requestDate");
                String aid = rs.getString("aid");
                int admin_approve = rs.getInt("admin_approve");
                int isExistAccount = rs.getInt("isExistAccount");
                String newUsername = rs.getString("newUsername");
                String owner = rs.getString("ownerApartmentId");

                RepresentResidentChangeRequest re = new RepresentResidentChangeRequest(id, residentDAO.getById_v2(oldRepresentPerson),
                        residentDAO.getById_v2(newRepresentPerson), aid, admin_approve, requestDate, isExistAccount, newUsername,
                        residentDAO.getById_v2(owner));
                list.add(re);
            }
            return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(RepresentResidentChangeRequestDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertNewRequest(RepresentResidentChangeRequest rs) {
        String sql = "insert into LivingAparmentRepresentResidentChangeRequest(oldRepresentPerson,\n"
                + "newRepresentPerson,\n"
                + "aId,requestDate,isExistAccount, admin_approve, newUsername, ownerApartmentId) values(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, rs.getOldRepresentPerson().getpId());
            ps.setString(2, rs.getNewRepresentPerson().getpId());
            ps.setString(3, rs.getaId());
            ps.setString(4, rs.getRequestDate());
            ps.setInt(5, rs.getIsExistAccount());
            ps.setInt(6, rs.getAdminApprove());
            ps.setString(7, rs.getNewUsername());
            ps.setString(8, rs.getOwner().getpId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(RepresentResidentChangeRequestDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateStatus(String id, String approve) {
        String sql = "update LivingAparmentRepresentResidentChangeRequest set admin_approve = ? where id = ? ";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, approve);
            ps.setString(2, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(RepresentResidentChangeRequestDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    public int checkStatus(String id) {
        String sql = "select admin_approve from LivingAparmentRepresentResidentChangeRequest where id =?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("admin_approve");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(RepresentResidentChangeRequestDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return -1;
    }

    public RepresentResidentChangeRequest getById(String rid) {
        String sql = "select * from LivingAparmentRepresentResidentChangeRequest where id  = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, rid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String oldRepresentPerson = rs.getString("oldRepresentPerson");
                String newRepresentPerson = rs.getString("newRepresentPerson");
                String requestDate = rs.getString("requestDate");
                String aid = rs.getString("aid");
                int admin_approve = rs.getInt("admin_approve");
                int isExistAccount = rs.getInt("isExistAccount");
                String newUsername = rs.getString("newUsername");
                String owner = rs.getString("ownerApartmentId");

                RepresentResidentChangeRequest re = new RepresentResidentChangeRequest(id, residentDAO.getById_v2(oldRepresentPerson),
                        residentDAO.getById_v2(newRepresentPerson), aid, admin_approve, requestDate, isExistAccount, newUsername,
                        residentDAO.getById_v2(owner));
                return re;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(RepresentResidentChangeRequestDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        RepresentResidentChangeRequestDAO dao = new RepresentResidentChangeRequestDAO();
        System.out.println(dao.getAllPendingList().size());
    }
}
