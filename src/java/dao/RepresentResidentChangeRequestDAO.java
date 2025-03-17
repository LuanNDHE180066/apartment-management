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
import model.RepresentResidentChangeRequest;
import model.Resident;

/**
 *
 * @author quang
 */
public class RepresentResidentChangeRequestDAO extends DBContext {

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

                RepresentResidentChangeRequest re = new RepresentResidentChangeRequest(id, oldRepresentPerson,
                        newRepresentPerson, aid, admin_approve, requestDate, isExistAccount);
                list.add(re);
            }
            return list;
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
