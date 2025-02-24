/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import jdbc.DBContext;
import model.Expenditure;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Company;
import model.Staff;

/**
 *
 * @author PC
 */
public class ExpenditureDAO extends DBContext{
    public List<Expenditure> getAll(){
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
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
