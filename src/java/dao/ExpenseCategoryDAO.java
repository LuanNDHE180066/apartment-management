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
        String sql = "select * from ExpenseCategory";
        List<ExpenseCategory> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ExpenseCategoryId");
                String categoryName = rs.getString("categoryName");
                String categoryDescription = rs.getString("categoryDescription");

                ExpenseCategory e = new ExpenseCategory(id, categoryName, categoryDescription);
                list.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpenseCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
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

                ExpenseCategory e = new ExpenseCategory(id, categoryName, categoryDescription);
                return e;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ExpenseCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
