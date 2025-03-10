/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.response.EmailInvoice;
import jdbc.DBContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBContext;
import model.Apartment;
import model.LivingApartment;
import model.OwnerApartment;
import model.Resident;

/**
 *
 * @author thanh
 */
public class LivingApartmentDAO extends DBContext {

    public List<LivingApartment> getByApartmentID(String aid) {
        String sql = "select * from LivingAparment where aId=?";
        ResidentDAO rd = new ResidentDAO();
        List<LivingApartment> list = new ArrayList<>();
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
                LivingApartment la = new LivingApartment(id, re, a, startDate, endDate, status);
                list.add(la);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public LivingApartment getLivingResidentByApartmentID(String aid) {
        String sql = "select * from LivingAparment where aId= ? and status  = 1";
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
                LivingApartment la = new LivingApartment(id, re, a, startDate, endDate, status);
                return la;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String generateID() {
        String sql = "select id from LivingAparment";
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

    public boolean insertLivingApartment(String rid, String aid, String startDate) {
        String sql = "insert into LivingAparment(id,rid,aid,Startdate, Enddate, status) values(?,?,?,?,?,1)";
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

    public boolean updateEndLivingApartment(String endDate, String aid) {
        String sql = "update LivingAparment set Enddate = ?, status = 0 where aid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, endDate);
            ps.setString(2, aid);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(LivingApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Apartment> getApartmentsByResidentId(String id) {
        ApartmentDAO ad = new ApartmentDAO();
        List<Apartment> list = new ArrayList<>();
        String sql = "select * from LivingAparment where rid =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(ad.getById(rs.getString("aid")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<String> getAllActiveApartment() {
        String sql = "select distinct(aid) as aid from LivingAparment where status =1";
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("aid"));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<EmailInvoice> getEmailInvoicesActiveResident() {
        String sql = "select * from LivingAparment la join Resident r on la.rId=r.Id where status =1";
        List<EmailInvoice> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String aid = rs.getString("aid");
                String email = rs.getString("email");
                EmailInvoice ei = new EmailInvoice(aid, email);
                list.add(ei);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<LivingApartment> getAllActiveLivingApartmentObejct() {
        String sql = "select * from LivingAparment where status =1";
        List<LivingApartment> list = new ArrayList<>();
        ApartmentDAO ad = new ApartmentDAO();
        ResidentDAO rd = new ResidentDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(rs.getString("aid"));
                String startDate = rs.getDate("startDate").toString();
                String endDate;
                if (rs.getDate("enddate") == null) {
                    endDate = null;
                } else {
                    endDate = rs.getDate("enddate").toString();
                }
                int status = rs.getInt("status");
                LivingApartment la = new LivingApartment(id, re, a, startDate, endDate, status);
                list.add(la);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public int getNumberLivingResident() {
        String sql = "select  count(*) as no from LivingAparment where status =1 ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("no");
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public int getNumberLivingByTime(int month, int year) {
        String sql = "		DECLARE @month INT = ?;  -- Tháng cần kiểm tra\\n\"\n"
                + "                DECLARE @year INT = ?; -- Năm cần kiểm tra\\n\"\n"
                + "                DECLARE @date DATETIME = DATEFROMPARTS(@year, @month, 1);\n"
                + "                SELECT count(*) as no \n"
                + "                FROM LivingAparment\n"
                + "                WHERE @date >= startdate\n"
                + "                AND (@date <= enddate OR enddate IS NULL);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, month);
            st.setInt(2, year);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("no");
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public static void main(String[] args) {
        LivingApartmentDAO dao = new LivingApartmentDAO();
        ResidentDAO daoR = new ResidentDAO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String date = now.format(formatter);
        LivingApartment oa = dao.getLivingResidentByApartmentID("A001");
        Resident ownerResident = daoR.getById("P102");
        oa.setRid(ownerResident);
        oa.setEndDate(date);
        oa.setStatus(0);

        oa.setStatus(1);
        oa.setEndDate(null);
        oa.setStartDate(date);
        System.out.println(dao.getByApartmentID("A001").size());
//        ResidentDAO daoR = new ResidentDAO();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate now = LocalDate.now();
//        String date = now.format(formatter);
//        LivingApartment oa = dao.getLivingResidentByApartmentID("A001");
//        Resident ownerResident = daoR.getById("P102");
//        oa.setRid(ownerResident);
//        oa.setEndDate(date);
//        oa.setStatus(0);
//
//        oa.setStatus(1);
//        oa.setEndDate(null);
//        oa.setStartDate(date);
//        System.out.println(dao.updateEndLivingApartment("2025-2-16", "A001"));
//        System.out.println(dao.getApartmentsByResidentId("P101").size());
//        System.out.println(dao.getAllActiveLivingApartmentObejct().size());
        System.out.println(dao.getNumberLivingByTime(2, 2025));
    }
}
