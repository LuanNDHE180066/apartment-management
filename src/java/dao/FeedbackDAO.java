/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.File;
import jdbc.DBContext;
import java.util.List;
import model.Resident;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.AbstractList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBContext;
import model.Account;
import model.Employee;
import model.Role;
import model.Company;
import model.Feedback;
import model.Request;
import model.RequestType;
import model.Staff;
import java.sql.Timestamp;
import util.Util;

/**
 *
 * @author quang
 */
public class FeedbackDAO extends DBContext {

    public List<Feedback> getAllFeedback() {
        String sql = "SELECT * FROM Feedback";
        ResidentDAO daoR = new ResidentDAO();
        RequestTypeDAO daoRT = new RequestTypeDAO();
        List<Feedback> list = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                List<String> img = getFeedbackImgs(rs.getString("id"));
                list.add(new Feedback(
                        rs.getString("id"),
                        rs.getString("detail"),
                        rs.getString("date"),
                        daoR.getById(rs.getString("rid")),
                        daoRT.getById(rs.getString("tid")),
                        rs.getInt("rate"),
                        img,
                        rs.getInt("Status")
                ));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getLastId() {
        String sql = "select top 1 id from Feedback order by date desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String lastId = rs.getString("id");
                return lastId;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    public List<Feedback> getFeedbackByRole(String role) {
        FeedbackDAO dao = new FeedbackDAO();
        List<Feedback> list = dao.getAllFeedback();
        List<Feedback> listFeedbackGetByRole = new ArrayList<>();
        if (role.equals("2")) {
            return list;
        }
        for (Feedback f : list) {
            if (f.getRequestType().getDestination().getId().equals(role)) {
                listFeedbackGetByRole.add(f);
            }
        }
        return listFeedbackGetByRole;
    }

    public List<Feedback> getFeebackAfterFilter(List<Feedback> list, String role) {
        FeedbackDAO dao = new FeedbackDAO();
        List<Feedback> listFeedbackGetByRole = new ArrayList<>();
        if (role.equals("2")) {
            return list;
        }
        for (Feedback f : list) {
            if (f.getRequestType().getDestination().getId().equals(role)) {
                listFeedbackGetByRole.add(f);
            }
        }
        return listFeedbackGetByRole;
    }

    public int sendFeedback(String detail, String rID, String tID, int rate, List<String> img) {
        String sql = "INSERT INTO Feedback (Id, Detail, Date, rId, tId, rate) VALUES (?, ?, ?, ?, ?, ?)";

        String lastId = getLastId();

        Util u = new Util();
        if (lastId == null) {
            lastId = "F0";
        } else {
            lastId = "F" + u.getNumberFromTextPlusOne(lastId);
        }
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, lastId);
            st.setString(2, detail);
            st.setTimestamp(3, currentTime);
            st.setString(4, rID);
            st.setString(5, tID);
            st.setInt(6, rate);

            st.executeUpdate();
            if (img != null) {
                insertImgFeedback(img, lastId); // insert list img to db
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public List<Feedback> getAllFeedbackUser(String residentID, int page, int pageSize) {
        String sql = "SELECT * FROM Feedback WHERE rId = ? ORDER BY id desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        ResidentDAO daoR = new ResidentDAO();
        RequestTypeDAO daoRT = new RequestTypeDAO();
        List<Feedback> list = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, residentID);
            ps.setInt(2, (page - 1) * pageSize);
            ps.setInt(3, pageSize);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                List<String> img = getFeedbackImgs(rs.getString("id"));
                list.add(new Feedback(
                        rs.getString("id"),
                        rs.getString("detail"),
                        rs.getString("date"),
                        daoR.getById(rs.getString("rid")),
                        daoRT.getById(rs.getString("tid")),
                        rs.getInt("rate"),
                        img,
                        rs.getInt("Status")
                ));
            }

            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int getTotalFeedbackCount(String residentId) {
        String sql = "SELECT COUNT(*) FROM Feedback WHERE rId = ?";
        int count = 0;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, residentId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Feedback> getByResidentName(List<Feedback> listFeedback, String name) {
        String sql = "select * from Feedback f join Resident r on r.Id = f.rId  where name like '%" + name + "%'";
        List<Feedback> list = new ArrayList<>();
        ResidentDAO daoR = new ResidentDAO();
        RequestTypeDAO daoRT = new RequestTypeDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<String> img = getFeedbackImgs(rs.getString("id"));
                list.add(new Feedback(
                        rs.getString("id"),
                        rs.getString("detail"),
                        rs.getString("date"),
                        daoR.getById(rs.getString("rid")),
                        daoRT.getById(rs.getString("tid")),
                        rs.getInt("rate"),
                        img,
                        rs.getInt("Status")
                ));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Feedback> getByDate(List<Feedback> listFeedback, String startDate, String endDate) {
        String sql = "select * from Feedback where date ";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (startDate != null) {
            Date date = Date.valueOf(startDate);
            String formatDate = format.format(date);
            sql += ">= '" + formatDate + "'";
        }
        if (endDate != null) {
            Date date = Date.valueOf(endDate);
            String formatDate = format.format(date);
            sql += " and date <= '" + formatDate + "'";
        }
        List<Feedback> list = new ArrayList<>();
        ResidentDAO daoR = new ResidentDAO();
        RequestTypeDAO daoRT = new RequestTypeDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<String> img = getFeedbackImgs(rs.getString("id"));
                list.add(new Feedback(
                        rs.getString("id"),
                        rs.getString("detail"),
                        rs.getString("date"),
                        daoR.getById(rs.getString("rid")),
                        daoRT.getById(rs.getString("tid")),
                        rs.getInt("rate"),
                        img,
                        rs.getInt("Status")
                ));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Feedback> getByServiceType(List<Feedback> listFeedback, String serviceId) {
        String sql = "select * from Feedback where tid = '" + serviceId + "'";
        List<Feedback> list = new ArrayList<>();
        ResidentDAO daoR = new ResidentDAO();
        RequestTypeDAO daoRT = new RequestTypeDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<String> img = getFeedbackImgs(rs.getString("id"));
                list.add(new Feedback(
                        rs.getString("id"),
                        rs.getString("detail"),
                        rs.getString("date"),
                        daoR.getById(rs.getString("rid")),
                        daoRT.getById(rs.getString("tid")),
                        rs.getInt("rate"),
                        img,
                        rs.getInt("Status")
                ));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Feedback> filterFeedback(String residentName, String serviceId, String startDate, String endDate, String role) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Feedback f JOIN Resident r ON r.Id = f.rId WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (residentName != null && !residentName.trim().isEmpty()) {
            sql.append(" AND r.name LIKE ?");
            params.add("%" + residentName + "%");
        }
        if (serviceId != null && !serviceId.trim().isEmpty()) {
            sql.append(" AND f.tid = ?");
            params.add(serviceId);
        }
        if (startDate != null && !startDate.trim().isEmpty()) {
            sql.append(" AND f.date >= ?");
            params.add(Date.valueOf(startDate)); // Safe conversion
        }
        if (endDate != null && !endDate.trim().isEmpty()) {
            sql.append(" AND f.date <= ?");
            params.add(Date.valueOf(endDate)); // Safe conversion
        }
        sql.append(" order by date desc");

        List<Feedback> list = new ArrayList<>();
        ResidentDAO daoR = new ResidentDAO();
        RequestTypeDAO daoRT = new RequestTypeDAO();

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    List<String> img = getFeedbackImgs(rs.getString("id"));
                    list.add(new Feedback(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            daoR.getById(rs.getString(4)),
                            daoRT.getById(rs.getString(5)),
                            rs.getInt(6),
                            img,
                            rs.getInt(7)
                    ));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getFeebackAfterFilter(list, role);
    }

    public List<Feedback> getPageByNumber(List<Feedback> list, int page, int number) {
        List<Feedback> listpage = new ArrayList<>();
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

    public void deleteFB(String id) {
        try {
            String sql = "delete from [Feedback] where id=?";
            if (getFeedbackImgs(id) != null) {
                deleteFBImg(id);
            }
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean deleteFBImg(String id) {
        try {
            String deleteImg = "delete from FeedbackImages where feedbackId=?";
            PreparedStatement st = connection.prepareStatement(deleteImg);
            st.setString(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public List<Feedback> getByResidentIDAndDateAndTypeRequest(String id, String from, String to, String requestType) {
        StringBuilder sql = new StringBuilder("SELECT * FROM feedback WHERE rId = ?");
        List<Feedback> list = new ArrayList<>();
        ResidentDAO daoR = new ResidentDAO();
        RequestTypeDAO daoRT = new RequestTypeDAO();

        // Handle optional parameters
        if (from != null && to != null) {
            sql.append(" AND (date BETWEEN ? AND ?)");
        }
        if (requestType != null && !requestType.isEmpty()) {
            sql.append(" AND tId = ?");
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());
            st.setString(1, id);

            int paramIndex = 2;
            if (from != null && to != null) {
                st.setString(paramIndex++, from);
                st.setString(paramIndex++, to);
            }
            if (requestType != null && !requestType.isEmpty()) {
                st.setString(paramIndex++, requestType);
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                List<String> img = getFeedbackImgs(rs.getString("id"));
                list.add(new Feedback(
                        rs.getString("id"),
                        rs.getString("detail"),
                        rs.getString("date"),
                        daoR.getById(rs.getString("rid")),
                        daoRT.getById(rs.getString("tid")),
                        rs.getInt("rate"),
                        img,
                        rs.getInt("Status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger for real applications
        }
        System.out.println(list.size());
        return list;
    }

    public List<String> getFeedbackImgs(String id) {
        String sql = "select * from FeedbackImages where feedbackId=?";
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(2));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    

    public boolean insertImgFeedback(List<String> imgs, String fId) {
        String sql = "INSERT INTO [dbo].[FeedbackImages] ([img], [feedbackId]) VALUES (?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            for (String img : imgs) {
                st.setString(1, img);
                st.setString(2, fId);
                st.addBatch();
            }

            st.executeBatch();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public Feedback getById(String id) {
        String sql = "Select * from Feedback where id=?";
        ResidentDAO daoR = new ResidentDAO();
        RequestTypeDAO daoRT = new RequestTypeDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<String> img = getFeedbackImgs(rs.getString("id"));
                Feedback f = new Feedback(
                        rs.getString("id"),
                        rs.getString("detail"),
                        rs.getString("date"),
                        daoR.getById(rs.getString("rid")),
                        daoRT.getById(rs.getString("tid")),
                        rs.getInt("rate"),
                        img,
                        rs.getInt("Status")
                );
                return f;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public boolean editFeedback(Feedback f) {
        String sql = "UPDATE [dbo].[Feedback]\n"
                + "SET \n"
                + "    Detail=?,\n"
                + "    [Date]=?,\n"
                + "    tId=?,\n"
                + "Status=?\n"
                + "WHERE id=?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, f.getDetail());
            st.setString(2, f.getDate());
            st.setString(3, f.getRequestType().getId());
            st.setInt(4, f.getStatus());
            st.setString(5, f.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteOneFeedbackImg(String oldImg) {
        String sql = "delete from FeedbackImages  where img?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, oldImg);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e
            );
        }
        return false;
    }

    public static void main(String[] args) {
        FeedbackDAO dao = new FeedbackDAO();
        Feedback f = dao.getById("F0");
        f.setStatus(0);
        System.out.println(dao.editFeedback(f));
    }
}
