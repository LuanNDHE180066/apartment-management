/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import jdbc.DBContext;
import model.Company;
import model.Expenditure;
import model.Staff;
import util.Util;

/**
 *
 * @author PC
 */
public class ExpenditureDAO extends DBContext {

    public List<Expenditure> getAll() {
        List<Expenditure> list = new ArrayList<>();
        String sql = "select * from Expenditure";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                int amount = rs.getInt("amount");
                float price = rs.getFloat("price");
                String approveddate = rs.getString("approveddate");
                String paymentdate = rs.getString("paymentdate");
                String note = rs.getString("note");
                String category = rs.getString("category");
                CompanyDAO cdao = new CompanyDAO();
                StaffDAO sdao = new StaffDAO();
                Company company = cdao.getById(rs.getString("cid"));
                Staff staff = sdao.getById(rs.getString("sid"));
                Expenditure ne = new Expenditure(id, amount, price, approveddate, paymentdate, note, category, company, staff);
                System.out.println("new ="+ne);
                list.add(ne);
            }
        } catch (Exception e) {
        }
        return list;
    }
    public List<String> getListCategory(){
        List<String> list = new ArrayList<>();
        String sql = "select distinct category from Expenditure";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {     
                String category = rs.getString("category");
                list.add(category);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Expenditure> getViewExpenditure(String title,String startDate,String endDate, String categories){
        List<Expenditure> list = new ArrayList<>();
        String sql = "select * from Expenditure where id <> '0'";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (title != "") {
            sql += " and title = '%" + title + "%'";
        }
        if (startDate != "") {
            Date date = Date.valueOf(startDate);
            String formatDate = format.format(date);
            sql += " and startdate >= '" + formatDate + "'";
        }

        if (endDate != "") {
            Date date = Date.valueOf(endDate);
            String formatDate = format.format(date);
            sql += " and startdate <= '" + formatDate + "'";
        }
        if (categories != "") {
            sql += " and category = '%" + categories + "%'";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                int amount = rs.getInt("amount");
                float price = rs.getFloat("price");
                String approveddate = rs.getString("approveddate");
                String paymentdate = rs.getString("paymentdate");
                String note = rs.getString("note");
                String category = rs.getString("category");
                CompanyDAO cdao = new CompanyDAO();
                StaffDAO sdao = new StaffDAO();
                Company company = cdao.getById(rs.getString("cid"));
                Staff staff = sdao.getById(rs.getString("sid"));
                Expenditure ne = new Expenditure(id, amount, price, approveddate, paymentdate, note, category, company, staff);
                list.add(ne);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public boolean addExpen(Expenditure e) {
        String sql = "insert into Expenditure (Id,amount, Price, Approveddate, Paymentdate, note, category, cid,sId) values(?,?,?,?,?,?,?,?,?)";
        Util u = new Util();
        ExpenditureDAO ed = new ExpenditureDAO();
        List<Expenditure> listEx = ed.getAll();
        int lastNum = 0;
        if (listEx.size() != 0) {
            lastNum = u.getNumberFromTextOnlyNumber(listEx.get(listEx.size() - 1).getId());
        }
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, (lastNum+1)+ "");
            ps.setInt(2, e.getAmount());
            ps.setFloat(3, e.getPrice());
            ps.setString(4, e.getApproveddate());
            ps.setString(5, e.getPaymentdate());
            ps.setString(6, e.getNote());
            ps.setString(7, e.getCategory());
            ps.setString(8, e.getCid().getId());
            ps.setString(9, e.getSid().getId());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        ExpenditureDAO dao = new ExpenditureDAO();
        StaffDAO std = new StaffDAO();
        CompanyDAO cpd = new CompanyDAO();
        Expenditure ex = new Expenditure();
        ex.setId("2");
        ex.setAmount(2);
        ex.setPrice(500);
        ex.setApproveddate("2025-02-24");
        ex.setPaymentdate("2025-02-24");
        ex.setNote("sua ong");
        ex.setCategory("chi");
        ex.setCid(cpd.getById("C004"));
        ex.setSid(std.getById("S1003"));
        System.out.println(dao.addExpen(ex));
//        System.out.println(dao.getAll().size());
    }
}
