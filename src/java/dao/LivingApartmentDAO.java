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
import java.sql.Date;
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

    private ResidentDAO residenDAO = new ResidentDAO();

    public List<LivingApartment> getByApartmentID(String aid, String startDate1, String endDate1) {
        String sql = "select * from LivingAparment where aId = ? ";
//                +"order by status desc, enddate desc";
        ResidentDAO rd = new ResidentDAO();
        List<LivingApartment> list = new ArrayList<>();
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
                int isRepresent = rs.getInt("isRepresent");
                LivingApartment la = new LivingApartment(id, re, a, startDate, endDate, status, isRepresent);
                list.add(la);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<LivingApartment> getActiveLivingResidentByApartmentID(String aid) {
        String sql = "select * from LivingAparment where aId=? and status = 1 order by startDate";
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
                int isRepresent = rs.getInt("isRepresent");
                LivingApartment la = new LivingApartment(id, re, a, startDate, endDate, status, isRepresent);
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
                int isRepresent = rs.getInt("isRepresent");
                LivingApartment la = new LivingApartment(id, re, a, startDate, endDate, status, isRepresent);
                return la;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String generateID() {
        String sql = "SELECT id FROM LivingAparment";
        List<Integer> list = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String idStr = rs.getString(1);
                if (idStr != null && !idStr.isEmpty()) {
                    list.add(Integer.parseInt(idStr));
                }
            }

            // If no records exist, start from 1
            int newID = list.isEmpty() ? 1 : Collections.max(list) + 1;

            return String.valueOf(newID);
        } catch (SQLException ex) {
            ex.printStackTrace(); // Print error for debugging
        }

        return null; // Return null if there's an error
    }

    public String getLivingResidentName(String aid) {
        String sql = "select rId from LivingAparment where aId = ? and status = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, aid);
            ResultSet rs = ps.executeQuery();
            StringBuilder str = new StringBuilder();
            while (rs.next()) {
                str.append(residenDAO.getById(rs.getString("rid")).getName());

                str.append(",");

            }
            return str.toString().trim();
        } catch (SQLException ex) {
            Logger.getLogger(LivingApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Resident> getLivingResidentList(String aid) {
        String sql = " select * from LivingAparment where  aId = ? and status = 1";
        List<Resident> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, aid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Resident r = residenDAO.getById_v2(rs.getString("rid"));
                list.add(r);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(LivingApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getLivingResidentId(String aid) {
        String sql = "select rId from LivingAparment where aId = ? and status = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, aid);
            ResultSet rs = ps.executeQuery();
            StringBuilder str = new StringBuilder();
            while (rs.next()) {
                str.append(rs.getString("rid"));

                str.append(",");

            }
            return str.toString().trim();
        } catch (SQLException ex) {
            Logger.getLogger(LivingApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            ex.printStackTrace();
        }
        return false;
    }

    public boolean insertMultipleLivingApartments(List<String> residentIds, List<String> apartmentIds, String startDate) {
        String sql = "INSERT INTO LivingAparment(id, rid, aid, Startdate, Enddate, status) VALUES (?, ?, ?, ?, ?, 1)";

        try {
            connection.setAutoCommit(false); // Start transaction
            PreparedStatement ps = connection.prepareStatement(sql);

            if (residentIds.size() != apartmentIds.size()) {
                throw new IllegalArgumentException("Mismatch between resident IDs and apartment IDs count.");
            }

            for (int i = 0; i < residentIds.size(); i++) {
                ps.setString(1, generateID()); // Assuming generateID() creates a unique ID
                ps.setString(2, residentIds.get(i));
                ps.setString(3, apartmentIds.get(i));
                ps.setString(4, startDate);
                ps.setNull(5, java.sql.Types.VARCHAR); // Enddate is null
                ps.addBatch(); // Add to batch
            }

            int[] results = ps.executeBatch(); // Execute all insertions
            connection.commit(); // Commit transaction

            // Check if all insertions were successful
            for (int result : results) {
                if (result <= 0) {
                    throw new SQLException("One or more insertions failed.");
                }
            }
            return true;

        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            try {
                connection.rollback(); // Rollback on error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true); // Reset auto-commit
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getNumberOfLivingPerson(String aid) {
        String sql = "select count (*) as nop from LivingAparment where aId = ? and status  = 1";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, aid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("nop");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivingApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean updateEndLivingApartment(String endDate, String aid, String rid) {
        String sql = "update LivingAparment set Enddate = ?, status = 0 where aid = ? and status = 1 and rId = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, endDate);
            ps.setString(2, aid);
            ps.setString(3, rid);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(LivingApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Apartment> getApartmentsByResidentId(String id) {
        ApartmentDAO ad = new ApartmentDAO();
        List<Apartment> list = new ArrayList<>();
        String sql = "select * from LivingAparment where rid =? and isRepresent =1 and status =1";
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

    public List<Apartment> getLivingApartmentsByResidentId(String id) {
        ApartmentDAO ad = new ApartmentDAO();
        List<Apartment> list = new ArrayList<>();
        String sql = "select * from LivingAparment where rid =? and status =1";
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
        String sql = "select * from LivingAparment la join Resident r on la.rId=r.Id where status =1 and la.isRepresent =1 ";
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

    public List<LivingApartment> getAllActiveOwnerLivingApartmentObejct() {
        String sql = "select * from LivingAparment where isRepresent =1 and status =1 ";
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
                int isRepresent = rs.getInt("isRepresent");
                LivingApartment la = new LivingApartment(id, re, a, startDate, endDate, status, isRepresent);
                list.add(la);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<LivingApartment> getAllNonRepresentResident(String aid) {
        String sql = "select * from LivingAparment where status = 1 and aId = ? and isRepresent = 0";
        ApartmentDAO ad = new ApartmentDAO();
        ResidentDAO rd = new ResidentDAO();
        List<LivingApartment> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, aid);
            ResultSet rs = ps.executeQuery();
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
                int isRepresent = rs.getInt("isRepresent");
                LivingApartment la = new LivingApartment(id, re, a, startDate, endDate, status, isRepresent);
                list.add(la);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(LivingApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public LivingApartment getRepresentedResidentByAid(String aid) {
        String sql = "select * from LivingAparment where status = 1 and aId = ? and isRepresent = 1";
        ApartmentDAO ad = new ApartmentDAO();
        ResidentDAO rd = new ResidentDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, aid);
            ResultSet rs = ps.executeQuery();
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
                int isRepresent = rs.getInt("isRepresent");
                LivingApartment la = new LivingApartment(id, re, a, startDate, endDate, status, isRepresent);
                return la;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivingApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkIsRepresentOfThisApartment(String pid, String aid) {
        String sql = "select * from LivingAparment where rId = ? and status = 1  and isRepresent = 1 and aid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pid);
            ps.setString(2, aid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivingApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getRepresentPersonId(String aid) {
        String sql = "select rid from LivingAparment where status = 1  and isRepresent = 1 and aid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, aid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("rid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivingApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkIsRepresent(String pid) {
        String sql = "select * from LivingAparment where rId = ? and status = 1  and isRepresent = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivingApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void changeIsRepresent(String isRepresent, String rid, String aid) {
        String sql = "update LivingAparment set isRepresent = ? where rId = ? and aId = ? and status = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, isRepresent);
            ps.setString(2, rid);
            ps.setString(3, aid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivingApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                + "                AND (@date <= enddate OR enddate IS NULL) and isrepresent =1 and status =1;";
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
//        System.out.println(dao.getApartmentsByResidentId("P134"));
//        System.out.println(dao.getNumberLivingByTime(2, 2025));
        System.out.println(dao.generateID());
    }
}
