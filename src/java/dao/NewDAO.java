/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBContext;
import model.Account;
import model.Employee;
import model.Role;
import model.Company;
import model.Feedback;
import model.News;
import model.RequestType;
import model.Staff;
import util.Util;

/**
 *
 * @author quang
 */
public class NewDAO extends DBContext {

    public List<News> getAllNews() {
        String sql = "select * from News order by date desc";
        StaffDAO daoSt = new StaffDAO();
        List<News> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new News(rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("source"),
                        rs.getString("category"),
                        rs.getString("image"),
                        daoSt.getById(rs.getString("sid")),
                        rs.getString("date")));

            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(NewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public News getNewById(String id) {
        String sql = "select * from news where id  = ?";
        StaffDAO daoSt = new StaffDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = new News(rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("source"),
                        rs.getString("category"),
                        rs.getString("image"),
                        daoSt.getById(rs.getString("sid")),
                        rs.getString("date"));
                return n;
            }

        } catch (SQLException ex) {
            Logger.getLogger(NewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<News> getOtherNews(String id) {
        String sql = "select * from news where id != ?";
        StaffDAO daoSt = new StaffDAO();
        List<News> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new News(rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("source"),
                        rs.getString("category"),
                        rs.getString("image"),
                        daoSt.getById(rs.getString("sid")),
                        rs.getString("date")));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(NewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<String> getAllCategory() {
        String sql = "select distinct category from news";
        StaffDAO daoSt = new StaffDAO();
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("category"));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(NewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void DeleteNews(String id){
        try {
            String sql="delete from news where id=?";
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
    public List<News> filterNews(String title, String startDate, String endDate) {
        String sql = "select * from News where 1 = 1 ";
        FeedbackDAO dao = new FeedbackDAO();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (title != "") {
            sql += " and title like '%" + title + "%'";
        }
        if (startDate != "") {
            Date date = Date.valueOf(startDate);
            String formatDate = format.format(date);
            sql += " and date >= '" + formatDate + "'";
        }
        if (endDate != "") {
            Date date = Date.valueOf(endDate);
            String formatDate = format.format(date);
            sql += " and date <= '" + formatDate + "'";
        }
        
        sql += " order by date desc";
        List<News> list = new ArrayList<>();
        StaffDAO daoSt = new StaffDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new News(rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("source"),
                        rs.getString("category"),
                        rs.getString("image"),
                        daoSt.getById(rs.getString("sid")),
                        rs.getString("date")));
            }
            return list;

        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateNews(News news) {
        String sql = "update news set title = ?, date = ?, image = ?, source = ?, content = ?, category = ?  where id = ?";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getDate());
            ps.setString(3, news.getImage());
            ps.setString(4, news.getSource());
            ps.setString(5, news.getContent());
            ps.setString(6, news.getCategory());
            ps.setString(7, news.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(NewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getNewId() {
        String sql = "select id from news";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Integer> li = new ArrayList<>();
            int max = 1;
            while (rs.next()) {
                li.add(Integer.parseInt(rs.getString("id")));
                for (Integer in : li) {
                    if (in > max) {
                        max = in;
                    }
                }
            }
            return max + 1;
        } catch (SQLException ex) {

        }
        return 1;
    }

    public boolean insertNews(News anew) {
        String sql = "insert into News(Id,title,Content,[source],category,image,sId,date) "
                + "values(?,?,?,?,?,?,?,?)";
        int id = this.getNewId();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, anew.getTitle());
            ps.setString(3, anew.getContent());
            ps.setString(4, anew.getSource());
            ps.setString(5, anew.getCategory());
            ps.setString(6, anew.getImage());
            ps.setString(7, anew.getStaff().getId());
            ps.setString(8, anew.getDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        NewDAO daoN = new NewDAO();
        StaffDAO stdao = new StaffDAO();
        String id = "10";
        String title = "Khởi động chương trình tình nguyện mùa hè 2026";
        String content = "Nội dung cho bản ghi Phung Nhat QUang 171";
        String source = "Nguồn 5";
        String category = "Hoat Dong";
        String image = "cc";
        Staff s = stdao.getById("S1002");
        String date = "02/12/2025";
        News news = new News(id, title, content, source, category, image, s, date);
        System.out.println(daoN.filterNews("a","","").size());
    }
}
