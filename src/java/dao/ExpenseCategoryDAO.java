/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import jdbc.DBContext;
import model.ExpenseCategory;
import java.sql.Date;
import jdbc.DBContext;
import model.Expenditure;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import model.Company;
import model.Staff;

import java.sql.SQLException;
import java.sql.SQLException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quang
 */
public class ExpenseCategoryDAO extends DBContext {

    public List<ExpenseCategory> getAllExpenseCategory() {
        String sql = "select * from ExpenseCategory where status = 1";
        List<ExpenseCategory> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ExpenseCategoryId");
                String categoryName = rs.getString("categoryName");
                String categoryDescription = rs.getString("categoryDescription");
                int status = rs.getInt("status");

                ExpenseCategory e = new ExpenseCategory(id, categoryName, categoryDescription, status);
                list.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ExpenseCategory> filterExpenseCategory(String search) {
        String sql = "select * from ExpenseCategory where status = 1 ";
        List<ExpenseCategory> list = new ArrayList<>();
        if (search != "") {
            sql += " and categoryname like '%" + search + "%'";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ExpenseCategoryId");
                String categoryName = rs.getString("categoryName");
                String categoryDescription = rs.getString("categoryDescription");
                int status = rs.getInt("status");

                ExpenseCategory e = new ExpenseCategory(id, categoryName, categoryDescription, status);
                list.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean addExpenseCategory(ExpenseCategory e) {
        String sql = "Insert into ExpenseCategory(categoryname, categorydescription,status) values(?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, e.getCategoryName());
            ps.setString(2, e.getCategoryDescription());
            ps.setInt(3, 1);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateExpenseCategory(ExpenseCategory e) {
        String sql = "update ExpenseCategory set CategoryName = ?, CategoryDescription = ?, status = ? where expensecategoryid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, e.getCategoryName());
            ps.setString(2, e.getCategoryDescription());
            ps.setInt(3, e.getStatus());
            ps.setInt(4, e.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ExpenseCategory getExpenseCategoryById(int id) {
        String sql = "select * from ExpenseCategory where ExpenseCategoryId = " + id;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int cid = rs.getInt("ExpenseCategoryId");
                String categoryName = rs.getString("categoryName");
                String categoryDescription = rs.getString("categoryDescription");
                int status = rs.getInt("status");

                ExpenseCategory e = new ExpenseCategory(id, categoryName, categoryDescription, status);
                return e;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ExpenseCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        ExpenseCategoryDAO dao = new ExpenseCategoryDAO();
        int id = 1;
        String name = "ABC";
        String des = "Tiền điện tiền nhà tiền nước";
        int status = 1;
        ExpenseCategory e = new ExpenseCategory(id, name, des, status);
        System.out.println(dao.filterExpenseCategory("se"));
    }
}
