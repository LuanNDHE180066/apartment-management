/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
import model.Resident;

/**
 *
 * @author thanh
 */
public class OwnerApartmentDAO extends DBContext {

    public List<OwnerApartment> getByApartmentID(String aid, String startDate1, String endDate1) {
        String sql = "select * from AparmentOwner where aId=?";
        ResidentDAO rd = new ResidentDAO();
        List<OwnerApartment> list = new ArrayList<>();
        ApartmentDAO ad = new ApartmentDAO();

        if (startDate1 != "") {
            sql += " and startDate >=  '" + startDate1 + "' ";
        }
        if (endDate1 != "") {
            sql += " and startDate <= '" + endDate1 + "' ";
        }
        sql += "  order by status desc, enddate desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, aid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(aid);
                String startDate = rs.getDate("startDate").toString();
                String endDate;
                if (rs.getDate("enddate") == null) {
                    endDate = null;
                } else {
                    endDate = rs.getDate("enddate").toString();
                }
                int status = rs.getInt("status");
                OwnerApartment oa = new OwnerApartment(id, re, a, startDate, endDate, status);
                list.add(oa);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public OwnerApartment getOwnerByApartmentID(String aid) {
        String sql = "select * from AparmentOwner where aId=? and status = 1";
        ResidentDAO rd = new ResidentDAO();

        ApartmentDAO ad = new ApartmentDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, aid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(aid);
                String startDate = rs.getDate("startDate").toString();
                String endDate;
                if (rs.getDate("enddate") == null) {
                    endDate = null;
                } else {
                    endDate = rs.getDate("enddate").toString();
                }
                int status = rs.getInt("status");
                OwnerApartment oa = new OwnerApartment(id, re, a, startDate, endDate, status);
                return oa;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String generateID() {
        String sql = "select id from AparmentOwner";
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(Integer.parseInt(rs.getString(1)));
            }
            return (Collections.max(list) + 1) + "";
        } catch (SQLException ex) {

        }
        return null;
    }

    public boolean insertOwnerApartment(String rid, String aid, String startDate) {
        String sql = "insert into AparmentOwner(id,rid,aid,Startdate, Enddate, status) values(?,?,?,?,?,1)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, this.generateID());
            ps.setString(2, rid);
            ps.setString(3, aid);
            ps.setString(4, startDate);
            ps.setString(5, null);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {

        }
        return false;
    }

    public boolean updateEndOwnerApartment(String id, String endDate) {
        String sql = "update AparmentOwner set Enddate = ?, status = 0 where aid = ? and status = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, endDate);
            ps.setString(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public static void main(String[] args) {
        OwnerApartmentDAO dao = new OwnerApartmentDAO();
        ResidentDAO daoR = new ResidentDAO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String date = now.format(formatter);
//        OwnerApartment oa = dao.getOwnerByApartmentID("A001");
//        Resident ownerResident = daoR.getById("P102");
//        oa.setRid(ownerResident);
//        oa.setEndDate(date);
//        oa.setStatus(0);

        System.out.println(dao.updateEndOwnerApartment("A14_04", "2025-02-16"));
    }
}
